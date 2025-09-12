package com.guibarao.seguranca_privada.dao.interfaces;

import com.guibarao.seguranca_privada.models.Solicitacao;
import com.guibarao.seguranca_privada.models.StatusSolicitacoes;
import com.guibarao.seguranca_privada.services.SolicitacaoService;

import java.time.LocalDate;
import java.util.List;

public interface SolicitacaoDAO {

    public Long createSolicitacao(Solicitacao solicitacao);

    public Solicitacao buscarById(Long id);

    public Boolean updateAtendente(Long idSolicitante, Long idAtendente);

    public boolean updateStatusSolicitacao(Long idSolicitacao, StatusSolicitacoes status);

    public List<Solicitacao> readSolicitacoes(LocalDate dataInicial, LocalDate dataFinal,
                                              StatusSolicitacoes status,
                                              Long idAtendente, Long idSolicitante);

}
