package com.guibarao.seguranca_privada.dao.impl;

import com.guibarao.seguranca_privada.dao.interfaces.SolicitacaoDAO;
import com.guibarao.seguranca_privada.models.Solicitacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SolicitacaoDAOImpl implements SolicitacaoDAO {

    private final Connection connection;

    public SolicitacaoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Long createSolicitacao(Solicitacao solicitacao) {
        String query = "INSERT INTO solicitacao (descricao, id_cliente) VALUES (?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, solicitacao.getDescricao());
            stmt.setLong(2, solicitacao.getIdSolicitante());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (!rs.next()) {
                return null;
            }

            return rs.getLong(1);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
