package com.guibarao.seguranca_privada.dao.impl;

import com.guibarao.seguranca_privada.dao.interfaces.UsuarioDAO;
import com.guibarao.seguranca_privada.models.Usuarios.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class UsuarioDAOImpl implements UsuarioDAO {

    private final Connection connection;

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Long createUsuario(Usuario novoUsuario)  {
        String query = "INSERT INTO usuario " +
                "(nome_usuario, nome_completo, senha, tipo, telefone) VALUES " +
                "(?, ?, ?, ?, ?)";

        try {
            PreparedStatement smt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            TipoUsuario tipoUsuario = novoUsuario.getTipoUsuario();

            smt.setString(1, novoUsuario.getNomeUsuario());
            smt.setString(2, novoUsuario.getNomeCompleto());
            smt.setString(3, novoUsuario.getSenha());
            System.out.println(tipoUsuario.name());
            smt.setString(4, tipoUsuario.name());



            smt.setString(5,  novoUsuario.getTipoUsuario() == TipoUsuario.CLIENTE ?
                                            ((Cliente) novoUsuario).getTelefone() : null);



            int linhasAfetadas = smt.executeUpdate();

            if(linhasAfetadas == 0) {
                throw new SQLException("Erro no cadastro.");
            }

            ResultSet idGerado = smt.getGeneratedKeys();

            idGerado.next();

            return idGerado.getLong(1);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    public Usuario buscarUsuarioByUsername(String nomeUsuario) {

        String query = "SELECT * FROM usuario WHERE nome_usuario = ?";

        try{
            PreparedStatement smt = connection.prepareStatement(query);
            smt.setString(1, nomeUsuario);

            ResultSet result = smt.executeQuery();

            if(!result.next()) {
                return null;
            }


            return resultSetToUsuario(result);

        }
        catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }


    }

    private Usuario resultSetToUsuario(ResultSet result) throws SQLException {

        return switch (result.getString("tipo")) {
          case "CLIENTE" -> Cliente.builder()
                            .id(result.getLong("id"))
                            .nomeCompleto(result.getString("nome_completo"))
                            .nomeUsuario(result.getString("nome_usuario"))
                            .senha(result.getString("senha"))
                            .ativo(result.getString("status").equals("ATIVO"))
                            .build();

          case "SEGURANCA", "ADMINISTRADOR" -> Funcionario.builder()
                              .id(result.getLong("id"))
                              .nomeCompleto(result.getString("nome_completo"))
                              .nomeUsuario(result.getString("nome_usuario"))
                              .senha(result.getString("senha"))
                              .ativo(result.getString("status").equals("ATIVO"))
                              .tipoUsuario(TipoUsuario.valueOf(result.getString("tipo")))
                              .build();

          default -> null;
        };

    }
}
