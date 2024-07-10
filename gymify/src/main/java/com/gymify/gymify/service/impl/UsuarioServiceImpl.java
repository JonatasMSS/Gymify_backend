package com.gymify.gymify.service.impl;

import com.gymify.gymify.model.entity.Usuario;
import com.gymify.gymify.model.repository.UsuarioRepository;
import com.gymify.gymify.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public Page<Usuario> buscarTodosUsuariosPaginados(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());

        return repository.save(usuario);
    }

    @Override
    public void deletarUsuario(Usuario usuario) {

        Objects.requireNonNull(usuario.getId());

        repository.delete(usuario);
    }

    @Override
    public Usuario getUsuario(Long id) {
        obterUsuarioPorId(id);
        return repository.findById(id).get();
    }

    @Override
    public Optional<Usuario> obterUsuarioPorId(Long id) {
        return repository.findById(id);
    }
}
