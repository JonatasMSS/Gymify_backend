package com.gymify.gymify.service.impl;

import com.gymify.gymify.model.entity.DadoUsuario;
import com.gymify.gymify.model.entity.Treino;
import com.gymify.gymify.model.repository.DadosUsuarioRepository;
import com.gymify.gymify.service.DadosUsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class DadosUsuarioServiceImpl implements DadosUsuarioService {

    private final DadosUsuarioRepository repository;

    public DadosUsuarioServiceImpl(DadosUsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public Page<DadoUsuario> buscarTodosDadosPaginados(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public DadoUsuario salvarDados(DadoUsuario dados) {
        return repository.save(dados);
    }

    @Override
    public DadoUsuario atualizarDados(DadoUsuario dados) {
        Objects.requireNonNull(dados.getId_dado());

        return repository.save(dados);
    }

    @Override
    public void deletarDados(DadoUsuario dados) {
        Objects.requireNonNull(dados.getId_dado());

        repository.delete(dados);
    }

    @Override
    public DadoUsuario getDados(Long id) {
        obterDadosPorId(id);
        return repository.findById(id).get();
    }

    @Override
    public Optional<DadoUsuario> obterDadosPorId(Long id) {
        return repository.findById(id);
    }
}
