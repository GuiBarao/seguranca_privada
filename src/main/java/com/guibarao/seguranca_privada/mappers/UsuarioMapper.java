package com.guibarao.seguranca_privada.mappers;

import com.guibarao.seguranca_privada.dtos.usuario.UsuarioCadastroDTO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioPublicDTO;
import com.guibarao.seguranca_privada.models.Usuarios.Administrador;
import com.guibarao.seguranca_privada.models.Usuarios.Cliente;

import com.guibarao.seguranca_privada.models.Usuarios.Seguranca;
import com.guibarao.seguranca_privada.models.Usuarios.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    default Usuario toModel(UsuarioCadastroDTO dadosCadastro) {
        return switch(dadosCadastro.tipo()) {
            case CLIENTE -> toCliente(dadosCadastro);
            case ADM -> toAdministrador(dadosCadastro);
            case SEGURANCA -> toSeguranca(dadosCadastro);
        };
    }

    @Mapping(target="plano", ignore = true)
    @Mapping(target="enderecos", ignore = true)
    @Mapping(target="telefone", ignore = true)
    @Mapping(target="parcelas", ignore = true)
    Cliente toCliente(UsuarioCadastroDTO dadosCadastro);


    Administrador toAdministrador(UsuarioCadastroDTO dadosCadastro);

    @Mapping(target="indisponibilidade", ignore = true)
    Seguranca toSeguranca(UsuarioCadastroDTO dadosCadastro);

    @Mapping(target="tipo", expression="java(usuario.getTipoUsuario())")
    @Mapping(target="ativo", constant = "true")
    UsuarioPublicDTO toPublicDTO(Usuario usuario);
}
