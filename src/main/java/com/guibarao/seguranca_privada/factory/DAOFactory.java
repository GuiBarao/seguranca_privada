package com.guibarao.seguranca_privada.factory;

import java.sql.Connection;

import com.guibarao.seguranca_privada.dao.impl.*;
import com.guibarao.seguranca_privada.dao.interfaces.*;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Component
public class DAOFactory {

    private Connection connection;

    public EnderecoDAO getEnderecoDAO() {
        return new EnderecoDAOImpl(connection);
    }

    public UsuarioDAO getUsuarioDAO() {return new UsuarioDAOImpl(connection);}

    public PlanoDAO getPlanoDAO() {return new PlanoDAOImpl(connection);}

    public SolicitacaoDAO getSolicitacaoDAO() {return new SolicitacaoDAOImpl(connection);}

    public EquipamentoDAO getEquipamentoDAO() {return new EquipamentoDAOImpl(connection);}
}

