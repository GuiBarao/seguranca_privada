package com.guibarao.seguranca_privada.services;

import com.guibarao.seguranca_privada.dao.interfaces.SolicitacaoDAO;
import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoAtendimentoDTO;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoService {

    private final ConnectionFactory connectionFactory;
    private final SolicitacaoMapper solicitacaoMapper;

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
            novaSolicitacao.setSolicitado_em(LocalDateTime.now());
            return solicitacaoMapper.toDTO(novaSolicitacao);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    public SolicitacaoPublicDTO buscarSolicitacao(Long idSolicitacao) {
        try(Connection connection = connectionFactory.getConnection()) {

            DAOFactory daoFactory = new DAOFactory(connection);
            SolicitacaoDAO solicitacaoDAO = daoFactory.getSolicitacaoDAO();

            Solicitacao solicitacaoEncontrada = solicitacaoDAO.buscarById(idSolicitacao);

            if(solicitacaoEncontrada == null || solicitacaoEncontrada.getStatus() == StatusSolicitacoes.DELETADA) {
                return null;
            }

            return solicitacaoMapper.toDTO(solicitacaoEncontrada);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public SolicitacaoPublicDTO atenderSolicitacao(SolicitacaoAtendimentoDTO dadosAtendimento) {
        try(Connection connection = connectionFactory.getConnection()) {

            DAOFactory daoFactory = new DAOFactory(connection);
            SolicitacaoDAO solicitacaoDAO = daoFactory.getSolicitacaoDAO();

            Boolean alterado = solicitacaoDAO.updateAtendente(dadosAtendimento.idSolicitacao(), dadosAtendimento.idAtendente());

            if(!alterado) {
                return null;
            }

            Solicitacao solicitacaoAlterada = solicitacaoDAO.buscarById(dadosAtendimento.idSolicitacao());
            return solicitacaoMapper.toDTO(solicitacaoAlterada);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean excluirSolicitacao(Long idSolicitacao) {
       return alterarStatusSolicitacao(idSolicitacao, StatusSolicitacoes.DELETADA);
    }

    public Boolean aceitarSolicitacao(Long idSolicitacao) {
        return alterarStatusSolicitacao(idSolicitacao, StatusSolicitacoes.ACEITA);
    }

    public Boolean devolverSolicitacao(Long idSolicitacao) {
        return alterarStatusSolicitacao(idSolicitacao, StatusSolicitacoes.PENDENTE);
    }


    private Boolean alterarStatusSolicitacao(Long idSolicitacao, StatusSolicitacoes status) {
        try(Connection connection = connectionFactory.getConnection()) {

            DAOFactory daoFactory = new DAOFactory(connection);
            SolicitacaoDAO solicitacaoDAO = daoFactory.getSolicitacaoDAO();

            boolean excluido = solicitacaoDAO.updateStatusSolicitacao(idSolicitacao, status);

            if(!excluido) {
                return null;
            }

            return true;
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<SolicitacaoPublicDTO> consultarSolicitacoes( LocalDate dataInicial, LocalDate dataFinal,
                                                             StatusSolicitacoes status,
                                                             Long idAtendente, Long idSolicitante) {

        try(Connection connection = connectionFactory.getConnection()) {

            DAOFactory daoFactory = new DAOFactory(connection);
            SolicitacaoDAO solicitacaoDAO = daoFactory.getSolicitacaoDAO();

            List<Solicitacao> solicitacoes = solicitacaoDAO.readSolicitacoes(dataInicial, dataFinal, status, idAtendente, idSolicitante);

            return solicitacoes.stream().filter(s -> s.getStatus() != StatusSolicitacoes.DELETADA).map(solicitacaoMapper::toDTO).toList();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }



}
