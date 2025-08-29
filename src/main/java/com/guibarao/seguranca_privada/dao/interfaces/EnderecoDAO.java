package com.guibarao.seguranca_privada.dao.interfaces;

import com.guibarao.seguranca_privada.models.Endereco;

import java.util.List;

public interface EnderecoDAO {

    void createEnderecos(List<Endereco> enderecos, Long idUsuario);
}
