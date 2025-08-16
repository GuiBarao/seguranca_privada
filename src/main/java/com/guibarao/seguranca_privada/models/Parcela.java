package com.guibarao.seguranca_privada.models;
import java.time.LocalDate;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Parcela {
    private Long id;
    private LocalDate dataVencimento;
    private StatusCobranca status;
    private BigDecimal valor;

}