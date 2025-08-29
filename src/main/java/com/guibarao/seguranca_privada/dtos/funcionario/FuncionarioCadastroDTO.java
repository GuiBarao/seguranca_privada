package com.guibarao.seguranca_privada.dtos.funcionario;

import com.guibarao.seguranca_privada.dtos.usuario.UsuarioCadastroDTO;

public record FuncionarioCadastroDTO(
        String nomeCompleto,
        String nomeUsuario,
        String senha
) implements UsuarioCadastroDTO {}
