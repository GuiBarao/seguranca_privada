package com.guibarao.seguranca_privada.mappers;

import com.guibarao.seguranca_privada.dtos.cliente.ClienteCadastroDTO;
import com.guibarao.seguranca_privada.dtos.funcionario.FuncionarioCadastroDTO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioPublicDTO;
import com.guibarao.seguranca_privada.models.Usuarios.Cliente;

import com.guibarao.seguranca_privada.models.Usuarios.Funcionario;
import com.guibarao.seguranca_privada.models.Usuarios.TipoUsuario;
import com.guibarao.seguranca_privada.models.Usuarios.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses=EnderecoMapper.class)
public interface UsuarioMapper {



    @Mapping(target="plano", ignore = true)
    @Mapping(target="enderecos", source="enderecos", defaultExpression = "java(new ArrayList<Endereco>())")
    @Mapping(target="parcelas", ignore = true)
    Cliente toModel(ClienteCadastroDTO dadosCadastro);

    @Mapping(target="indisponibilidade", ignore=true)
    @Mapping(target="tipoUsuario", source = "tipoUsuario")
    Funcionario toModel(FuncionarioCadastroDTO dadosCadastro, TipoUsuario tipoUsuario);


    @Mapping(target="tipo", expression="java(usuario.getTipoUsuario())")
    @Mapping(target="ativo", constant = "true")
    UsuarioPublicDTO toPublicDTO(Usuario usuario);


}
