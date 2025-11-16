package com.guibarao.seguranca_privada.dao.impl;

import com.guibarao.seguranca_privada.dao.interfaces.EquipamentoDAO;
import com.guibarao.seguranca_privada.models.ItemEstoque;
import lombok.AllArgsConstructor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class EquipamentoDAOImpl implements EquipamentoDAO {

    private Connection connection;

    public Long cadastrarEquipamento(ItemEstoque dados){
        String query = "{ CALL registra_equipamento_estoque(?, ?, ?) }";
        try (CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, dados.getTelefoneFornecedor());
            stmt.setString(2, dados.getNome());
            stmt.setInt(3, dados.getQuantidadeEstoque());

            boolean sucesso = stmt.execute();

            if(sucesso){
                try(ResultSet rs = stmt.getResultSet()){
                    if (rs.next()) {
                        return rs.getLong("id_gerado");
                    }
                }
            }

            return null;

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
