package com.guibarao.seguranca_privada.models.Usuarios;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of="id")
@ToString
public abstract class Usuario {

    private Long id;
    private String nomeCompleto;
    private String nomeUsuario;
    private String senha;
    private boolean ativo = true;

    public abstract TipoUsuario getTipoUsuario();

}
