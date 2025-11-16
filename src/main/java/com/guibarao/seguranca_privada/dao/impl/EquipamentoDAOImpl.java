package com.guibarao.seguranca_privada.dao.impl;

import com.guibarao.seguranca_privada.dao.interfaces.EquipamentoDAO;
import com.guibarao.seguranca_privada.models.ItemEstoque;
import lombok.AllArgsConstructor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void alterarQuantidade(int idItem, int alteracao){
        String query = "{ CALL altera_quantidade_item(?, ?) }";

        try (CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setInt(1, idItem);
            stmt.setInt(2, alteracao);

            stmt.execute();


        }
        catch (SQLException e) {
            throw  new RuntimeException("Erro ao alterar quantidade: " + e.getMessage());
        }
    }

    public List<ItemEstoque> listarEquipamentosEmFalta() {
        String query = "{ CALL itens_em_falta() }";

        try (CallableStatement stmt = connection.prepareCall(query)) {

            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            List<ItemEstoque> itens =  new ArrayList<>();

            while (rs.next()) {
                itens.add(tuplaToModel(rs));
            }

            return itens;




        }
        catch (SQLException e) {
            throw  new RuntimeException("Erro ao alterar quantidade: " + e.getMessage());
        }
    }

    private ItemEstoque tuplaToModel(ResultSet rs) throws SQLException {
        return new ItemEstoque( rs.getLong(1),
                                            rs.getString(3),
                                            rs.getInt(5),
                                            rs.getString(4));


    }


}
