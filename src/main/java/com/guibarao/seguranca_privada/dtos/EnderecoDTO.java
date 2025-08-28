package com.guibarao.seguranca_privada.dtos;

public record EnderecoDTO(
        String cep,
        String numero,
        String bairro,
        String rua,
        String complemento)
{}
