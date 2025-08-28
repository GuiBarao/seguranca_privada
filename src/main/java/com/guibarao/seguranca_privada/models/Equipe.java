package com.guibarao.seguranca_privada.models;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.guibarao.seguranca_privada.models.Usuarios.Cliente;
import com.guibarao.seguranca_privada.models.Usuarios.Seguranca;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipe{
    private Map<Seguranca, List<Turno>> segurancas =  new HashMap<>();
    private boolean ativo;
    private Map<ItemEstoque, Integer> itensUtilizados = new HashMap<>();
    private Cliente clienteAtendido;


}