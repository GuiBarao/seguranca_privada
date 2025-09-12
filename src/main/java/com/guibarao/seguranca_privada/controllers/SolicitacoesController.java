package com.guibarao.seguranca_privada.controllers;

import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoAtendimentoDTO;
import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.solicitacao.SolicitacaoPublicDTO;
import com.guibarao.seguranca_privada.models.StatusSolicitacoes;
import com.guibarao.seguranca_privada.services.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<SolicitacaoPublicDTO>> listarSolicitacoes(@RequestParam(required = false) LocalDate dataInicial,
                                                                         @RequestParam(required = false) LocalDate dataFinal,
                                                                         @RequestParam(required = false) StatusSolicitacoes status,
                                                                         @RequestParam(required = false) Long idAtendente,
                                                                         @RequestParam(required = false) Long idSolicitante) {

        List<SolicitacaoPublicDTO> solicitacoes =  solicitacaoService.consultarSolicitacoes(dataInicial, dataFinal, status, idAtendente, idSolicitante);

        return ResponseEntity.status(HttpStatus.OK).body(solicitacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoPublicDTO> buscarSolicitacao(@PathVariable Long id) {
        SolicitacaoPublicDTO solicitacaoEncontrada = solicitacaoService.buscarSolicitacao(id);

        if(solicitacaoEncontrada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(solicitacaoEncontrada);

    }

    @PatchMapping("/atender")
    public ResponseEntity<SolicitacaoPublicDTO> atenderSolicitacao(@RequestBody @Valid SolicitacaoAtendimentoDTO dadosAtendimento) {

        SolicitacaoPublicDTO solicitacaoAlterada = solicitacaoService.atenderSolicitacao(dadosAtendimento);

        return ResponseEntity.status(HttpStatus.OK).body(solicitacaoAlterada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSolicitacao(@PathVariable Long id) {

        if(!solicitacaoService.excluirSolicitacao(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PatchMapping("/{id}/aceitar")
    public ResponseEntity<SolicitacaoPublicDTO> aceitarSolicitacao(@PathVariable Long id){
        if(!solicitacaoService.aceitarSolicitacao(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("{id}/devolver")
    public ResponseEntity<SolicitacaoPublicDTO> devolverSolicitacao(@PathVariable Long id){
        if(!solicitacaoService.devolverSolicitacao(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
