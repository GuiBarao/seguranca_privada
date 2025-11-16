package com.guibarao.seguranca_privada.dao.impl;

import com.guibarao.seguranca_privada.dao.interfaces.PlanoDAO;
import com.guibarao.seguranca_privada.models.Plano;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PlanoDAOImpl implements PlanoDAO {

    private Connection connection;

    private Plano buscarPlano(PreparedStatement statement) {

      try(ResultSet result = statement.executeQuery()) {
          if(!result.next()) {
              return null;
          }

          return resultSetToPlano(result);
      }
      catch (SQLException e) {
          System.out.println(e.getMessage());
          return null;
      }

    }

    @Override
    public Plano buscarPlanoById(Long id) {

        String query = "SELECT * FROM plano WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);

            return buscarPlano(statement);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

    public Plano buscarPlanoByNome(String nomePlano) {
        String query = "SELECT * FROM plano WHERE nome = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, nomePlano);

            return buscarPlano(statement);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Long createPlano(Plano plano) {
        String query = "INSERT INTO plano (nome, max_horas, max_segurancas, valor_mensal, max_localizacoes)" +
                " VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, plano.getNome());
            stmt.setInt(2, plano.getMaximoHorasAtendimento());
            stmt.setInt(3, plano.getMaxSegurancas());
            stmt.setBigDecimal(4, plano.getValor());
            stmt.setInt(5, plano.getMaximoLocalizacoes());


            int linhasAlteradas = stmt.executeUpdate();


            if(linhasAlteradas == 0) {
                return null;
            }

            ResultSet rsChaves = stmt.getGeneratedKeys();

            rsChaves.next();

            return rsChaves.getLong(1);

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Plano resultSetToPlano(ResultSet rs) throws SQLException {

        try{
            return new Plano(   rs.getLong("id"),
                    rs.getInt("max_horas"),
                    rs.getInt("max_segurancas"),
                    rs.getBigDecimal("valor_mensal"),
                    rs.getInt("max_localizacoes"),
                    rs.getString("nome"),
                    rs.getString("status").equals("ATIVO"));
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

    public void updatePlano(Plano plano) {
        StringBuilder query = new StringBuilder("UPDATE plano SET ");

        List<Object> parametros = new ArrayList<>();

        if (plano.getMaximoHorasAtendimento() != null) {
            query.append("max_horas = ?, ");
            parametros.add(plano.getMaximoHorasAtendimento());
        }

        if (plano.getMaxSegurancas() != null) {
            query.append("max_segurancas = ?, ");
            parametros.add(plano.getMaxSegurancas());
        }

        if (plano.getValor() != null) {
            query.append("valor_mensal = ?, ");
            parametros.add(plano.getValor());
        }

        if (plano.getNome() != null) {
            query.append("nome = ?, ");
            parametros.add(plano.getNome());
        }

        if (plano.getMaximoLocalizacoes() != null) {
            query.append("max_localizacoes = ?, ");
            parametros.add(plano.getMaximoLocalizacoes());
        }

        if (query.toString().endsWith(", ")) {
            query.delete(query.length() - 2, query.length());
        }

        query.append(" WHERE id = ?;");
        parametros.add(plano.getId());

        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                Object p = parametros.get(i);

                if (p instanceof String) stmt.setString(i + 1, (String) p);
                else if (p instanceof Integer) stmt.setInt(i + 1, (Integer) p);
                else if (p instanceof BigDecimal) stmt.setBigDecimal(i + 1, (BigDecimal) p);
            }

            stmt.setLong(parametros.size(), plano.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
