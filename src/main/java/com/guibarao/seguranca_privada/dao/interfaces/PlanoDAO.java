package com.guibarao.seguranca_privada.dao.interfaces;

import com.guibarao.seguranca_privada.models.Plano;

public interface PlanoDAO {

    public Plano buscarPlanoById(Long id);

    public Plano buscarPlanoByNome(String nomePlano);

    public Long createPlano(Plano plano);
}
