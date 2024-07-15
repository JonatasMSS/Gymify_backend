package com.gymify.gymify.service;

import com.gymify.gymify.model.entity.DadoUsuario;
import com.gymify.gymify.model.entity.Treino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DadosUsuarioService {
    Page<DadoUsuario> buscarTodosDadosPaginados(Pageable pageable);

    DadoUsuario salvarDados(DadoUsuario dados);

    DadoUsuario atualizarDados(DadoUsuario dados);

    void deletarDados(DadoUsuario dados);

    DadoUsuario getDados(Long id);

    Optional<DadoUsuario> obterDadosPorId(Long id);
}
