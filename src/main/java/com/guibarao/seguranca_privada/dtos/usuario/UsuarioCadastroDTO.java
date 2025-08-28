package com.guibarao.seguranca_privada.dtos.usuario;

import com.guibarao.seguranca_privada.models.Usuarios.TipoUsuario;

public record UsuarioCadastroDTO (String nomeCompleto,
                                  String nomeUsuario,
                                  String senha,
                                  TipoUsuario tipo)
{}
