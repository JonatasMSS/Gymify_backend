package com.gymify.gymify.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Treino")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treino {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "repeticoes")
    private String repeticoes;

    @Column(name = "descanso")
    private String descanso;

    @Column(name = "peso;")
    private int peso;

}
