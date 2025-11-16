package com.guibarao.seguranca_privada.models;
import java.math.BigDecimal;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plano{
    private Long id;
    private Integer maximoHorasAtendimento;
    private Integer maxSegurancas;
    private BigDecimal valor;
    private Integer maximoLocalizacoes;
    private String nome;
    private Boolean ativo;

    @Override
    public String toString() {
        return "Plano{" +
                "id=" + id +
                ", maximoHorasAtendimento=" + maximoHorasAtendimento +
                ", maxSegurancas=" + maxSegurancas +
                ", valor=" + valor +
                ", maximoLocalizacoes=" + maximoLocalizacoes +
                ", nome='" + nome + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}