package com.guibarao.seguranca_privada.dtos.cliente;

import com.guibarao.seguranca_privada.dtos.EnderecoDTO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioCadastroDTO;

import java.util.List;

public record ClienteCadastroDTO(
        String nomeCompleto,
        String nomeUsuario,
        String senha,
        String telefone,
        List<EnderecoDTO> enderecos,
        Long idPlano

) implements UsuarioCadastroDTO {}
