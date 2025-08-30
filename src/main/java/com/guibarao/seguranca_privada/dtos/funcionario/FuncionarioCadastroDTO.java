package com.guibarao.seguranca_privada.dtos.funcionario;

import com.guibarao.seguranca_privada.dtos.usuario.UsuarioCadastroDTO;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioCadastroDTO(

        @NotBlank(message="O nome completo deve ser informado.")
        String nomeCompleto,

        @NotBlank(message="O nome usuário deve ser informado.")
        String nomeUsuario,

        @NotBlank(message="A senha deve ser informada.")
        String senha
) implements UsuarioCadastroDTO {}
