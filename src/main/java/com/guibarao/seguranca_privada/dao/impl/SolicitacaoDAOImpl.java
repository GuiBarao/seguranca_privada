package com.guibarao.seguranca_privada.dao.impl;

import com.guibarao.seguranca_privada.dao.interfaces.SolicitacaoDAO;
import com.guibarao.seguranca_privada.models.Solicitacao;
import com.guibarao.seguranca_privada.models.StatusSolicitacoes;
import com.guibarao.seguranca_privada.models.Usuarios.Cliente;
import com.guibarao.seguranca_privada.models.Usuarios.Funcionario;

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

    @Override
    public Solicitacao buscarById(Long id) {

        String query = "SELECT solicitacao.*, \n" +
                "cliente.nome_usuario as nome_usuario_cliente, cliente.nome_completo as nome_completo_cliente, cliente.telefone as telefone_cliente, \n" +
                "atendente.nome_usuario as nome_usuario_atendente\n" +
                "FROM solicitacao\n" +
                "JOIN usuario as cliente on solicitacao.id_cliente = cliente.id\n" +
                "LEFT JOIN usuario as atendente on solicitacao.id_atendente = atendente.id\n" +
                "WHERE solicitacao.id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()) {
                return null;
            }

            return resultSetToModel(rs);

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Boolean updateAtendente(Long idSolicitante, Long idAtendente) {
        String query = "UPDATE solicitacao SET id_atendente = ?, status = 'ANALISE' WHERE id = ?;";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, idAtendente);
            ps.setLong(2, idSolicitante);

            int linhasAlteradas = ps.executeUpdate();

            return linhasAlteradas > 0;

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    private Solicitacao resultSetToModel(ResultSet rs) throws SQLException {

        Cliente solicitante = Cliente.builder()
                .id(rs.getLong("id_cliente"))
                .nomeCompleto(rs.getString("nome_completo_cliente"))
                .nomeUsuario(rs.getString("nome_usuario_cliente")).build();

        long idAtendenteRaw = rs.getLong("id_atendente");
        Long idAtendente = rs.wasNull() ? null : idAtendenteRaw;


        Funcionario atendente = idAtendente == null ? null : Funcionario.builder()
                .id(idAtendente)
                .nomeUsuario("nome_usuario_atendente")
                .build();


        return new Solicitacao( rs.getLong("id"),
                                rs.getString("descricao"),
                                StatusSolicitacoes.valueOf(rs.getString("status")),
                                solicitante,
                                atendente);
    }
}
