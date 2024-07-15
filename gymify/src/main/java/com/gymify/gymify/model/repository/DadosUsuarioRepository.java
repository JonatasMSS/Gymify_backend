package com.gymify.gymify.model.repository;

import com.gymify.gymify.model.entity.DadoUsuario;
import com.gymify.gymify.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosUsuarioRepository extends JpaRepository<DadoUsuario, Long> {

    Page<DadoUsuario> findAll(Pageable pageable);

}
