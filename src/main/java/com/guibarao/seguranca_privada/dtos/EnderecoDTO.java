package com.guibarao.seguranca_privada.dtos;
import jakarta.validation.constraints.*;

public record EnderecoDTO(
        @NotBlank(message="CEP deve ser informado.")
        String cep,

        @NotBlank(message="Numero do endereço deve ser informado.")
        String numero,

        @NotBlank(message="Bairro deve ser informado.")
        String bairro,

        @NotBlank(message="Rua deve ser informado.")
        String rua,

        String complemento)
{}
