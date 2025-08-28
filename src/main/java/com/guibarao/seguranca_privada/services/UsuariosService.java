package com.guibarao.seguranca_privada.services;

import com.guibarao.seguranca_privada.dao.interfaces.UsuarioDAO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioCadastroDTO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioPublicDTO;
import com.guibarao.seguranca_privada.factory.ConnectionFactory;
import com.guibarao.seguranca_privada.factory.DAOFactory;
import com.guibarao.seguranca_privada.mappers.UsuarioMapper;
import com.guibarao.seguranca_privada.models.Usuarios.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UsuariosService {


    private final ConnectionFactory connectionFactory;
    private final UsuarioMapper usuarioMapper;

    public UsuariosService(ConnectionFactory connectionFactory,
                           DAOFactory daoFactory,
                           UsuarioMapper usuarioMapper) {
        this.connectionFactory = connectionFactory;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioPublicDTO cadastrar(UsuarioCadastroDTO dadosUsuario) {

        Usuario novoUsuario = usuarioMapper.toModel(dadosUsuario);

        Connection connection;
        try{
            connection = connectionFactory.getConnection();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        DAOFactory daoFactory = new DAOFactory(connection);

        UsuarioDAO usuarioDAO = daoFactory.getUsuarioDAO();

        if(usuarioDAO.buscarUsuarioByUsername(novoUsuario.getNomeUsuario()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário já cadastrado.");
        }

        Long idNovoUsuario = usuarioDAO.create(novoUsuario);

        novoUsuario.setId(idNovoUsuario);

        return usuarioMapper.toPublicDTO(novoUsuario);

    }
}
