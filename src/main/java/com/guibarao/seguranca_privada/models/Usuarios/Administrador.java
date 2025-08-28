package com.guibarao.seguranca_privada.models.Usuarios;
import lombok.*;
import com.guibarao.seguranca_privada.models.Usuarios.Usuario;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Administrador extends Usuario {

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.ADM;
    }

}
