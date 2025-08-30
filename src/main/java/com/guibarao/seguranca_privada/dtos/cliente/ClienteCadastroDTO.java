package com.guibarao.seguranca_privada.dtos.cliente;

import com.guibarao.seguranca_privada.dtos.EnderecoDTO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioCadastroDTO;
import jakarta.validation.constraints.*;

import java.util.List;

public record ClienteCadastroDTO(

        @NotBlank(message="O nome completo deve ser informado.")
        String nomeCompleto,

        @NotBlank(message="O nome de usuário deve ser informado.")
        String nomeUsuario,

        @NotBlank(message="A senha deve ser informado.")
        String senha,

        @NotBlank(message="O telefone deve ser informado.")
        String telefone,

        @NotBlank(message="O(s) endereço(s) deve ser informado.")
        List<EnderecoDTO> enderecos,

        @NotNull(message="O(s) endereço(s) deve ser informado.")
        Long idPlano

) implements UsuarioCadastroDTO {}
