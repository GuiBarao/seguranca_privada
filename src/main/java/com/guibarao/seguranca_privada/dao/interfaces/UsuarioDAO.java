package com.guibarao.seguranca_privada.dao.interfaces;

import com.guibarao.seguranca_privada.models.Usuarios.Usuario;
import org.springframework.stereotype.Component;


@Component
public interface UsuarioDAO {
    Long createUsuario(Usuario usuario);

    Usuario buscarUsuarioByUsername(String nomeUsuario);
}
