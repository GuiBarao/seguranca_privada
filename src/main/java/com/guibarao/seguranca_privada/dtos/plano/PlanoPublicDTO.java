package com.guibarao.seguranca_privada.dtos.plano;

import java.math.BigDecimal;

public record PlanoPublicDTO(

        Long id,

        int maximoHorasAtendimento,

        int maxSegurancas,

        BigDecimal valor,

        int maximoLocalizacoes,

        String nome,

        boolean ativo
) {}
