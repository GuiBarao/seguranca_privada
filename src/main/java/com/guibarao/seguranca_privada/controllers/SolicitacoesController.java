package com.guibarao.seguranca_privada.controllers;

import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoPublicDTO;
import com.guibarao.seguranca_privada.services.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacoesController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacoesController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping
    public ResponseEntity<SolicitacaoPublicDTO> cadastrarSolicitacao(@RequestBody @Valid SolicitacaoCadastroDTO dadosNovaSolicitacao) {
        SolicitacaoPublicDTO novaSolicitacao = solicitacaoService.cadastrar(dadosNovaSolicitacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaSolicitacao);
    }
}
