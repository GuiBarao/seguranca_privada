package com.guibarao.seguranca_privada.models;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public abstract class Usuario {

    private Long id;
    private String nomeCompleto;
    private String nomeUsuario;
    private String senha;
    private boolean ativo;

}
