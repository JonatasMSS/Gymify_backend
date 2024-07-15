package com.gymify.gymify.dto;

import com.gymify.gymify.model.entity.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class DadosUsuarioDTO {

    private Long id_dado;
    private Long id_usuario;
    private float taxa_metabolica;
    private float braco;
    private float cintura;
    private float peito;
    private float pernas;


}
