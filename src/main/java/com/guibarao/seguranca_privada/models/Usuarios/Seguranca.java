package com.guibarao.seguranca_privada.models.Usuarios;
import lombok.*;
import java.util.List;
import com.guibarao.seguranca_privada.models.Indisponibilidade;
import com.guibarao.seguranca_privada.models.Usuarios.Usuario;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Seguranca extends Usuario {

    private List<Indisponibilidade> indisponibilidade;

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.SEGURANCA;
    }

}
