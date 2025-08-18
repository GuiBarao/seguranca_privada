package com.guibarao.seguranca_privada.models;
import lombok.*;
import java.util.List;
import com.guibarao.seguranca_privada.models.Indisponibilidade;

@Getter
@Setter
public class Seguranca extends Usuario {
    private List<Indisponibilidade> indisponibilidade;
}
