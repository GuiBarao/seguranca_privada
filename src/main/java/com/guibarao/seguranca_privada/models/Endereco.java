package com.guibarao.seguranca_privada.models;

public record Endereco(
        String cep,
        String numero,
        String bairro,
        String rua,
        String complemento)
{}