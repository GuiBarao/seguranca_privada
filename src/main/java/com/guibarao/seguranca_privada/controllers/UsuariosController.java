package com.guibarao.seguranca_privada.controllers;

import com.guibarao.seguranca_privada.dtos.usuario.UsuarioCadastroDTO;
import com.guibarao.seguranca_privada.dtos.usuario.UsuarioPublicDTO;
import com.guibarao.seguranca_privada.services.UsuariosService;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping
    public ResponseEntity<UsuarioPublicDTO> cadastrarUsuario(@RequestBody UsuarioCadastroDTO dadosCadastro) {

        UsuarioPublicDTO novoUsuario = usuariosService.cadastrar(dadosCadastro);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }


}
