package com.guibarao.seguranca_privada.controllers;

import com.guibarao.seguranca_privada.dtos.plano.PlanoCadastroDTO;
import com.guibarao.seguranca_privada.dtos.plano.PlanoPublicDTO;
import com.guibarao.seguranca_privada.services.PlanoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planos")
public class PlanosController {

    private PlanoService planoService;

    public PlanosController(PlanoService planoService) {
        this.planoService = planoService;
    }


    @PostMapping
    public ResponseEntity<PlanoPublicDTO> createPlano(@Valid @RequestBody
                                                          PlanoCadastroDTO dadosNovoPlano) {
        PlanoPublicDTO novoPlano = planoService.cadastrar(dadosNovoPlano);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
    }



}
