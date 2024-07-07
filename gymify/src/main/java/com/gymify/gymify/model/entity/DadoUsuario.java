package com.gymify.gymify.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DadosUsuarios")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadoUsuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dado;

    // Relacionamento com tabela de usuarios
    private Long id_usuario;

    @Column(name = "taxa_metabolica")
    private float taxa_metabolica;

    @Column(name = "braco")
    private float braco;

    @Column(name = "cintura")
    private float cintura;

    @Column(name = "peito")
    private float peito;

    @Column(name = "pernas")
    private float pernas;
}
