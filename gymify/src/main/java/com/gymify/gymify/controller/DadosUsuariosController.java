package com.gymify.gymify.controller;

import com.gymify.gymify.dto.DadosUsuarioDTO;
import com.gymify.gymify.exception.RegraNegocioException;
import com.gymify.gymify.model.entity.DadoUsuario;
import com.gymify.gymify.model.entity.Usuario;
import com.gymify.gymify.model.repository.DadosUsuarioRepository;
import com.gymify.gymify.service.DadosUsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dados")
public class DadosUsuariosController {
    
    private final DadosUsuarioService service;
    private final DadosUsuarioRepository repository;


    public DadosUsuariosController(DadosUsuarioService service, DadosUsuarioRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("{id}")
    public ResponseEntity getDados(@PathVariable("id") Long id) {
        DadoUsuario dados = service.getDados(id);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DadoUsuario>> buscarTodosDadoss(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DadoUsuario> dadosPaginados = service.buscarTodosDadosPaginados(pageRequest);

        return ResponseEntity.ok(dadosPaginados);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody DadosUsuarioDTO dto) {
        try {
            DadoUsuario entidade = converter(dto);

            entidade = service.salvarDados(entidade);
            return new ResponseEntity(entidade, HttpStatus.CREATED);

        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody DadosUsuarioDTO dto) {
        return service.obterDadosPorId(id).map( entity -> {
            try {
                DadoUsuario dados = entity;

                dados.setId_dado(dto.getId_dado());
                dados.setId_usuario(dto.getId_usuario());
                dados.setTaxa_metabolica(dto.getTaxa_metabolica());
                dados.setBraco(dto.getBraco());
                dados.setCintura(dto.getCintura());
                dados.setPeito(dto.getPeito());
                dados.setPernas(dto.getPernas());


                service.atualizarDados(dados);
                return ResponseEntity.ok(dados);
            } catch (RegraNegocioException e ) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Dados não encontrado.", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        return service.obterDadosPorId(id).map(entity -> {
            service.deletarDados(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Dados não encontrado.", HttpStatus.BAD_REQUEST));
    }

    private DadoUsuario converter(DadosUsuarioDTO dto) {
        DadoUsuario dados = new DadoUsuario();

        dados.setId_dado(dto.getId_dado());
        dados.setId_usuario(dto.getId_usuario());
        dados.setTaxa_metabolica(dto.getTaxa_metabolica());
        dados.setBraco(dto.getBraco());
        dados.setCintura(dto.getCintura());
        dados.setPeito(dto.getPeito());
        dados.setPernas(dto.getPernas());
        return dados;

    }
}
