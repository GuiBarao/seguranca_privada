package com.guibarao.seguranca_privada.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoque {
    private Long id;
    private String telefoneFornecedor;
    private Integer quantidadeEstoque;
    private String nome;
}