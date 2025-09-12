package com.guibarao.seguranca_privada.dtos.solicitacao;

import com.guibarao.seguranca_privada.models.StatusSolicitacoes;

import java.time.LocalDateTime;

public record SolicitacaoPublicDTO(
    Long id,
    String descricao,
    Long idSolicitante,
    String nomeUsuarioSolicitante,
    Long idAtendente,
    String nomeUsuarioAtendente,
    StatusSolicitacoes status,
    LocalDateTime solicitado_em

) {}
