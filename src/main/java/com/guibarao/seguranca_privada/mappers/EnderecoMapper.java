package com.guibarao.seguranca_privada.mappers;
import com.guibarao.seguranca_privada.dtos.EnderecoDTO;
import com.guibarao.seguranca_privada.models.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoDTO toDTO(Endereco endereco);
    Endereco toModel(EnderecoDTO endereco);
}
