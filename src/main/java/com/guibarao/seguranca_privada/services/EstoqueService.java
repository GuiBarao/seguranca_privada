package com.guibarao.seguranca_privada.services;

import com.guibarao.seguranca_privada.dao.interfaces.EquipamentoDAO;
import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoPublicDTO;
import com.guibarao.seguranca_privada.factory.ConnectionFactory;
import com.guibarao.seguranca_privada.factory.DAOFactory;
import com.guibarao.seguranca_privada.mappers.EquipamentoMapper;
import com.guibarao.seguranca_privada.models.ItemEstoque;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class EstoqueService {

    private final ConnectionFactory connectionFactory;
    private final EquipamentoMapper equipamentoMapper;

    public EstoqueService(ConnectionFactory connectionFactory, EquipamentoMapper equipamentoMapper) {
        this.connectionFactory = connectionFactory;
        this.equipamentoMapper = equipamentoMapper;

    }


    public EquipamentoPublicDTO cadastrar(EquipamentoCadastroDTO dados) {

        ItemEstoque equipamento = equipamentoMapper.toModel(dados);

        try(Connection connection = connectionFactory.getConnection()) {
            DAOFactory daoFactory = new DAOFactory(connection);
            EquipamentoDAO equipamentoDAO = daoFactory.getEquipamentoDAO();

            Long idGerado = equipamentoDAO.cadastrarEquipamento(equipamento);

            equipamento.setId(idGerado);

            return equipamentoMapper.toDTO(equipamento);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

}
