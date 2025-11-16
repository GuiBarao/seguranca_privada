package com.guibarao.seguranca_privada.dtos.equipamento;

public record EquipamentoPublicDTO(
        Long id,
        String telefoneFornecedor,
        int quantidadeEstoque,
        String nome
) {}
