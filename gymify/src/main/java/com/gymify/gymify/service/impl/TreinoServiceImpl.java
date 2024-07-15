package com.gymify.gymify.service.impl;

import com.gymify.gymify.model.entity.Treino;
import com.gymify.gymify.model.entity.Treino;
import com.gymify.gymify.model.repository.TreinoRepository;
import com.gymify.gymify.model.repository.TreinoRepository;
import com.gymify.gymify.service.TreinoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TreinoServiceImpl implements TreinoService {

    private final TreinoRepository repository;

    public TreinoServiceImpl(TreinoRepository repository) {
        this.repository = repository;
    }
    

    @Override
    public Page<Treino> buscarTodosTreinosPaginados(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Treino salvarTreino(Treino treino) {
        return repository.save(treino);
    }

    @Override
    public Treino atualizarTreino(Treino treino) {
        Objects.requireNonNull(treino.getId());

        return repository.save(treino);
    }

    @Override
    public void deletarTreino(Treino treino) {
        Objects.requireNonNull(treino.getId());

        repository.delete(treino);
    }

    @Override
    public Treino getTreino(Long id) {
        obterTreinoPorId(id);
        return repository.findById(id).get();
    }

    @Override
    public Optional<Treino> obterTreinoPorId(Long id) {
        return repository.findById(id);
    }
}
