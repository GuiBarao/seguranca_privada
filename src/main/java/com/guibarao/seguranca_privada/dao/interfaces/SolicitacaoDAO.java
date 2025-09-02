package com.guibarao.seguranca_privada.dao.interfaces;

import com.guibarao.seguranca_privada.models.Solicitacao;

public interface SolicitacaoDAO {

    public Long createSolicitacao(Solicitacao solicitacao);

    public Solicitacao buscarById(Long id);

    public Boolean updateAtendente(Long idSolicitante, Long idAtendente);
}
