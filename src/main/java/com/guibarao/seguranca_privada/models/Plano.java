package com.guibarao.seguranca_privada.models;
import java.math.BigDecimal;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plano{
    private Long id;
    private int maximoHorasAtendimento;
    private int maxSegurancas;
    private BigDecimal valor;
    private int maximoLocalizacoes;
    private String nome;
    private boolean ativo;
}