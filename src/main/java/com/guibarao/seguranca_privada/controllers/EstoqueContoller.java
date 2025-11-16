package com.guibarao.seguranca_privada.controllers;

import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoPublicDTO;
import com.guibarao.seguranca_privada.models.ItemEstoque;
import com.guibarao.seguranca_privada.services.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueContoller {

    EstoqueService estoqueService;

    public EstoqueContoller(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }


    @PostMapping
    public ResponseEntity<EquipamentoPublicDTO> registraEquipamento(@RequestBody EquipamentoCadastroDTO dados) {

        EquipamentoPublicDTO novoEquipamento = estoqueService.cadastrar(dados);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoEquipamento);

    }

    @PutMapping
    public void alterarQuantidade(@RequestParam int id_item, @RequestParam int alteracao) {

        estoqueService.alterarQuantidade(id_item, alteracao);

    }


    @GetMapping("/faltas")
    public ResponseEntity<List<ItemEstoque>> getFaltas() {

        List<ItemEstoque> itens = estoqueService.getFaltas();

        return ResponseEntity.status(HttpStatus.OK).body(itens);
    }
}
