package com.guibarao.seguranca_privada.dtos.plano;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PlanoAtualizacaoDTO(

        @Positive(message="O máximo de horas de atendimento do plano deve ser maior que 0.")
        Integer maximoHorasAtendimento,

        @Positive(message="O máximo de seguranças em atendimento do plano deve ser informado.")
        Integer maxSegurancas,

        @Positive(message="O valor mensal do plano deve ser positivo.")
        BigDecimal valor,

        @Positive(message="O máximo de localizações de atendimento no plano deve ser maior do que 0.")
        Integer maximoLocalizacoes,

        String nome,

        @NotNull(message="O id do plano a ser alterado deve ser informado.")
        Integer id



) {}
