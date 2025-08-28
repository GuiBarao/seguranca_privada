package com.guibarao.seguranca_privada.dao.impl;

import com.guibarao.seguranca_privada.dao.interfaces.EnderecoDAO;
import com.guibarao.seguranca_privada.models.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import lombok.*;

@Getter
@AllArgsConstructor
public class EnderecoDAOImpl implements EnderecoDAO {

    private Connection connection;


    public void create(Endereco endereco, Long idUsuario) {
        String query = "INSERT INTO endereco " +
                "(id_cliente, rua, bairro, complemento, cep, numero)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setLong(1, idUsuario);
            stmt.setString(2, endereco.rua());
            stmt.setString(3, endereco.bairro());

            if (endereco.complemento() != null) {
                stmt.setString(4, endereco.complemento());
            }
            else {
                stmt.setNull(4, Types.VARCHAR);
            }

            stmt.setString(5, endereco.cep());

            stmt.setString(6, endereco.numero());

            stmt.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
}
