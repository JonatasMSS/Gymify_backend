package com.gymify.gymify.service;

import com.gymify.gymify.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UsuarioService {

    Page<Usuario> buscarTodosUsuariosPaginados(Pageable pageable);

    Usuario salvarUsuario(Usuario usuario);

    Usuario atualizarUsuario(Usuario usuario);

    void deletarUsuario(Usuario usuario);

    Usuario getUsuario(Long id);

    Optional<Usuario> obterUsuarioPorId(Long id);


}
