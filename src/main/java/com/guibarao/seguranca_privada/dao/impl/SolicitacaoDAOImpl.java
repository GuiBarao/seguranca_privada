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
import java.sql.Date;
import java.sql.Types;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    public boolean updateStatusSolicitacao(Long idSolicitacao, StatusSolicitacoes status) {
        String query = "UPDATE solicitacao SET status = ? WHERE id = ?;";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status.name());
            ps.setLong(2, idSolicitacao);

            int linhasAlteradas = ps.executeUpdate();

            return linhasAlteradas > 0;

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
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

    public List<Solicitacao> readSolicitacoes(LocalDate dataInicial, LocalDate dataFinal,
                                              StatusSolicitacoes status,
                                              Long idAtendente, Long idSolicitante) {


        StringBuilder query = new StringBuilder("SELECT solicitacao.*, \n" +
                "cliente.nome_usuario as nome_usuario_cliente, cliente.nome_completo as nome_completo_cliente, cliente.telefone as telefone_cliente, \n" +
                "atendente.nome_usuario as nome_usuario_atendente\n" +
                "FROM solicitacao\n" +
                "JOIN usuario as cliente on solicitacao.id_cliente = cliente.id\n" +
                "LEFT JOIN usuario as atendente on solicitacao.id_atendente = atendente.id\n" +
                "WHERE 1=1");

        List<Object> parametros = new ArrayList<Object>();
        List<Integer> tipo_parametros = new ArrayList<Integer>();

        if(dataInicial != null && dataFinal != null) {
            query.append(" AND solicitacao.solicitado_em BETWEEN ? AND ?");
            parametros.add(Date.valueOf(dataInicial));
            parametros.add(Date.valueOf(dataFinal));
            tipo_parametros.add(Types.DATE);
            tipo_parametros.add(Types.DATE);
        }

        if(status != null) {
            query.append(" AND solicitacao.status = ?");
            parametros.add(status.name());
            tipo_parametros.add(Types.VARCHAR);
        }

        if(idAtendente != null) {
            query.append(" AND solicitacao.id_atendente = ?");
            parametros.add(idAtendente);
            tipo_parametros.add(Types.INTEGER);
        }

        if(idSolicitante != null) {
            query.append(" AND solicitacao.id_cliente = ?");
            parametros.add(idSolicitante);
            tipo_parametros.add(Types.INTEGER);
        }


        try(PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            System.out.println(query.toString());
            for(int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i), tipo_parametros.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            List<Solicitacao> solicitacoes = new ArrayList<>();
            while(rs.next()) {
                solicitacoes.add(resultSetToModel(rs));
            }

            return solicitacoes;

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
