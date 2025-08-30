package com.guibarao.seguranca_privada.dtos.plano;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;
public record PlanoCadastroDTO (

        @NotNull(message="O máximo de horas de atendimento do plano deve ser informado.")
        @Positive(message="O máximo de horas de atendimento do plano deve ser maior que 0.")
        int maximoHorasAtendimento,

        @NotNull(message="O máximo de horas de atendimento do plano deve ser maior do que 0.")
        @Positive(message="O máximo de seguranças em atendimento do plano deve ser informado.")
        int maxSegurancas,

        @NotNull(message="O valor mensal do plano deve ser informado.")
        @Positive(message="O valor mensal do plano deve ser positivo.")
        BigDecimal valor,

        @NotNull(message="O máximo de localizações de atendimento no plano deve ser informado.")
        @Positive(message="O máximo de localizações de atendimento no plano deve ser maior do que 0.")
        int maximoLocalizacoes,

        @NotBlank(message="O nome deve ser informado.")
        String nome
) {}
