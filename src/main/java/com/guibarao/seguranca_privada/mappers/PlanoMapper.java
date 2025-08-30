package com.guibarao.seguranca_privada.mappers;

import com.guibarao.seguranca_privada.dtos.plano.PlanoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.plano.PlanoPublicDTO;
import com.guibarao.seguranca_privada.models.Plano;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PlanoMapper {

    public Plano toModel(PlanoCadastroDTO plano);

    public PlanoPublicDTO toDTO(Plano plano);
}
