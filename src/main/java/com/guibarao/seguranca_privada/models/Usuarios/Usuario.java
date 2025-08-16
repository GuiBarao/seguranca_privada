package com.guibarao.seguranca_privada.models;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Usuario {

    private Long id;
    private String nomeCompleto;
    private String nomeUsuario;
    private String senha;
    private boolean ativo;

}
