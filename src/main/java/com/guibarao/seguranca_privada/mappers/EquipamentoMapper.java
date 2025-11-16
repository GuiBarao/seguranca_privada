package com.guibarao.seguranca_privada.mappers;

import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoPublicDTO;
import com.guibarao.seguranca_privada.models.ItemEstoque;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface EquipamentoMapper {

     ItemEstoque toModel(EquipamentoCadastroDTO estoque);

     EquipamentoPublicDTO toDTO(ItemEstoque itemEstoque);
}
