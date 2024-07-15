package com.gymify.gymify.controller;

import com.gymify.gymify.dto.UsuarioDTO;
import com.gymify.gymify.exception.RegraNegocioException;
import com.gymify.gymify.model.entity.Usuario;
import com.gymify.gymify.model.repository.UsuarioRepository;
import com.gymify.gymify.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioService service, UsuarioRepository repository, PasswordEncoder encoder) {
        this.service = service;
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("{id}")
    public ResponseEntity getUsuario(@PathVariable("id") Long id) {
        Usuario usuario = service.getUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<Usuario>> buscarTodosUsuarios(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Usuario> usuariosPaginados = service.buscarTodosUsuariosPaginados(pageRequest);

        return ResponseEntity.ok(usuariosPaginados);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
        try {
            Usuario entidade = converter(dto);

            entidade = service.salvarUsuario(entidade);
            return new ResponseEntity(entidade, HttpStatus.CREATED);

        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody UsuarioDTO dto) {
        return service.obterUsuarioPorId(id).map( entity -> {
            try {
                Usuario usuario = entity;

                usuario.setName(dto.getNome());
                usuario.setEmail(dto.getEmail());
                usuario.setSenha(encoder.encode(dto.getSenha()));

                service.atualizarUsuario(usuario);
                return ResponseEntity.ok(usuario);
            } catch (RegraNegocioException e ) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Usuario não encontrado.", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        return service.obterUsuarioPorId(id).map(entity -> {
            service.deletarUsuario(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Usuario não encontrado.", HttpStatus.BAD_REQUEST));
    }

    private Usuario converter(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setId(dto.getId());
        usuario.setName(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(encoder.encode(dto.getSenha()));
        return usuario;
    }
}
