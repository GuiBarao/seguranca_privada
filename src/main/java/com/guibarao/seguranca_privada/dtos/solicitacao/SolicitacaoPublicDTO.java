package com.guibarao.seguranca_privada.dtos.solicitacao;

import com.guibarao.seguranca_privada.models.StatusSolicitacoes;

public record SolicitacaoPublicDTO(
    Long id,
    String descricao,
    Long idSolicitante,
    Long idAtendente,
    StatusSolicitacoes status

) {}
