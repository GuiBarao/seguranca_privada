package com.guibarao.seguranca_privada.dao.interfaces;

import com.guibarao.seguranca_privada.models.Endereco;

public interface EnderecoDAO {

    void create(Endereco endereco, Long idUsuario);
}
