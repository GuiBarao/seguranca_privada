package com.guibarao.seguranca_privada.models;
import java.time.LocalDate;
import java.math.BigDecimal;
import lombok.*;
import com.guibarao.seguranca_privada.models.StatusParcela;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Parcela {
    private Long id;
    private LocalDate dataVencimento;
    private StatusParcela status;
    private BigDecimal valor;

}