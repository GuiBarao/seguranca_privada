package com.guibarao.seguranca_privada.mappers;

import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoPublicDTO;
import com.guibarao.seguranca_privada.models.Solicitacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitacaoMapper {

    @Mapping(source = "idCliente", target = "idSolicitante")
    @Mapping(source="descricao", target="descricao")
    public Solicitacao toModel(SolicitacaoCadastroDTO dadosCadastroSolicitacao);

    @Mapping(source="solicitante.id", target="idSolicitante")
    @Mapping(source="atendente.id", target="idAtendente")
    @Mapping(source="solicitante.nomeUsuario", target="nomeUsuarioSolicitante")
    @Mapping(source="atendente.nomeUsuario", target="nomeUsuarioAtendente")
    public SolicitacaoPublicDTO toDTO(Solicitacao solicitacao);
}
