package com.guibarao.seguranca_privada.services;

import com.guibarao.seguranca_privada.dao.interfaces.EnderecoDAO;
import com.guibarao.seguranca_privada.dao.interfaces.UsuarioDAO;
import com.guibarao.seguranca_privada.dtos.cliente.ClienteCadastroDTO;
import com.guibarao.seguranca_privada.dtos.funcionario.FuncionarioCadastroDTO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioPublicDTO;
import com.guibarao.seguranca_privada.factory.ConnectionFactory;
import com.guibarao.seguranca_privada.factory.DAOFactory;
import com.guibarao.seguranca_privada.mappers.UsuarioMapper;
import com.guibarao.seguranca_privada.models.Endereco;
import com.guibarao.seguranca_privada.models.Usuarios.Cliente;
import com.guibarao.seguranca_privada.models.Usuarios.Funcionario;
import com.guibarao.seguranca_privada.models.Usuarios.TipoUsuario;
import com.guibarao.seguranca_privada.models.Usuarios.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class UsuariosService {


    private final ConnectionFactory connectionFactory;
    private final UsuarioMapper usuarioMapper;

    public UsuariosService(ConnectionFactory connectionFactory,
                           UsuarioMapper usuarioMapper) {
        this.connectionFactory = connectionFactory;
        this.usuarioMapper = usuarioMapper;
    }

    private Long cadastrar(Usuario novoUsuario) {

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

        return usuarioDAO.createUsuario(novoUsuario);

    }

    private void cadastrar(List<Endereco> novosEnderecos, Long idUsuario) {
        try {
            Connection connection = connectionFactory.getConnection();

            DAOFactory daoFactory = new DAOFactory(connection);

            EnderecoDAO enderecoDAO = daoFactory.getEnderecoDAO();

            enderecoDAO.createEnderecos(novosEnderecos, idUsuario);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public UsuarioPublicDTO cadastrar(ClienteCadastroDTO dadosUsuario) {

        Cliente novoCliente = usuarioMapper.toModel(dadosUsuario);

        novoCliente.setId(cadastrar(novoCliente));

        if(!novoCliente.getEnderecos().isEmpty()) {
            
            cadastrar(novoCliente.getEnderecos(), novoCliente.getId());
        }

        return usuarioMapper.toPublicDTO(novoCliente);


    }

    public UsuarioPublicDTO cadastrar(FuncionarioCadastroDTO dadosUsuario, TipoUsuario tipoUsuario) {

        Funcionario novoFuncionario = usuarioMapper.toModel(dadosUsuario, tipoUsuario);

        novoFuncionario.setId(cadastrar(novoFuncionario));

        return usuarioMapper.toPublicDTO(novoFuncionario);
    }
}
