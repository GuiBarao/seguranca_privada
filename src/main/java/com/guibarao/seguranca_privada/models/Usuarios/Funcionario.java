package com.guibarao.seguranca_privada.models.Usuarios;
import com.guibarao.seguranca_privada.models.Indisponibilidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
public class Funcionario extends Usuario {

    TipoUsuario tipoUsuario;
    private List<Indisponibilidade> indisponibilidade =  new ArrayList<Indisponibilidade>();


}
