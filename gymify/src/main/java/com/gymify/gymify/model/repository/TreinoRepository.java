package com.gymify.gymify.model.repository;

import com.gymify.gymify.model.entity.Treino;
import com.gymify.gymify.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    Page<Treino> findAll(Pageable pageable);
}
