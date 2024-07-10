package com.gymify.gymify.model.repository;

import com.gymify.gymify.model.entity.DadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosUsuarioRepository extends JpaRepository<DadoUsuario, Long> {
}
