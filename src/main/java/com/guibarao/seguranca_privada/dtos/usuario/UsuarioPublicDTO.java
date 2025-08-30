package com.guibarao.seguranca_privada.dtos.usuario;

import com.guibarao.seguranca_privada.models.Usuarios.TipoUsuario;

public record UsuarioPublicDTO (

        Long id,

        String nomeUsuario,

        String nomeCompleto,

        boolean ativo,

        TipoUsuario tipo
)
{}
