package com.guibarao.seguranca_privada.controllers;

import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.equipamento.EquipamentoPublicDTO;
import com.guibarao.seguranca_privada.services.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
