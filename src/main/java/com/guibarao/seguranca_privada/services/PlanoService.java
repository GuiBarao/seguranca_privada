package com.guibarao.seguranca_privada.services;

import com.guibarao.seguranca_privada.dao.interfaces.PlanoDAO;
import com.guibarao.seguranca_privada.dtos.plano.PlanoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.plano.PlanoPublicDTO;
import com.guibarao.seguranca_privada.factory.ConnectionFactory;
import com.guibarao.seguranca_privada.factory.DAOFactory;
import com.guibarao.seguranca_privada.mappers.PlanoMapper;
import com.guibarao.seguranca_privada.models.Plano;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class PlanoService {

    private final ConnectionFactory connectionFactory;
    private final PlanoMapper planoMapper;

    public PlanoService(ConnectionFactory connectionFactory, PlanoMapper planoMapper) {
        this.connectionFactory = connectionFactory;
        this.planoMapper = planoMapper;

    }

    public PlanoPublicDTO cadastrar(PlanoCadastroDTO dadosNovoPlano) {
        Plano novoPlano = planoMapper.toModel(dadosNovoPlano);

        try(Connection connection = connectionFactory.getConnection()) {
            DAOFactory daoFactory = new DAOFactory(connection);
            PlanoDAO planoDAO = daoFactory.getPlanoDAO();

            if(planoDAO.buscarPlanoByNome(novoPlano.getNome()) != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Plano já registrado!");
            }


            Long idGerado = planoDAO.createPlano(novoPlano);

            novoPlano.setId(idGerado);
            return planoMapper.toDTO(novoPlano);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


}
