package com.gymify.gymify.service;

import com.gymify.gymify.model.entity.Treino;
import com.gymify.gymify.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TreinoService {

    Page<Treino> buscarTodosTreinosPaginados(Pageable pageable);

    Treino salvarTreino(Treino treino);

    Treino atualizarTreino(Treino Treino);

    void deletarTreino(Treino treino);

    Treino getTreino(Long id);

    Optional<Treino> obterTreinoPorId(Long id);


}
