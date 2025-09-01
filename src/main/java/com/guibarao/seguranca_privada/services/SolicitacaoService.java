package com.guibarao.seguranca_privada.services;

import com.guibarao.seguranca_privada.dao.interfaces.SolicitacaoDAO;
import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoPublicDTO;
import com.guibarao.seguranca_privada.factory.ConnectionFactory;
import com.guibarao.seguranca_privada.factory.DAOFactory;
import com.guibarao.seguranca_privada.mappers.SolicitacaoMapper;
import com.guibarao.seguranca_privada.models.Solicitacao;
import com.guibarao.seguranca_privada.models.StatusSolicitacoes;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class SolicitacaoService {

    private ConnectionFactory connectionFactory;
    private SolicitacaoMapper solicitacaoMapper;

    public SolicitacaoService(ConnectionFactory connectionFactory,
                              SolicitacaoMapper solicitacaoMapper) {
        this.connectionFactory = connectionFactory;
        this.solicitacaoMapper = solicitacaoMapper;
    }

    public SolicitacaoPublicDTO cadastrar(SolicitacaoCadastroDTO dadosNovaSolicitacao) {
        try(Connection connection = connectionFactory.getConnection()) {

            DAOFactory daoFactory = new DAOFactory(connection);
            SolicitacaoDAO solicitacaoDAO = daoFactory.getSolicitacaoDAO();

            Solicitacao novaSolicitacao = solicitacaoMapper.toModel(dadosNovaSolicitacao);

            Long idGerado = solicitacaoDAO.createSolicitacao(novaSolicitacao);

            novaSolicitacao.setId(idGerado);
            novaSolicitacao.setStatus(StatusSolicitacoes.PENDENTE);
            return solicitacaoMapper.toDTO(novaSolicitacao);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


}
