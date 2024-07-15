package com.gymify.gymify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreinoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String repeticoes;
    private String descanso;
    private int peso;
}
