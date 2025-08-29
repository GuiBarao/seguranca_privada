package com.guibarao.seguranca_privada.controllers;

import com.guibarao.seguranca_privada.dtos.cliente.ClienteCadastroDTO;
import com.guibarao.seguranca_privada.dtos.funcionario.FuncionarioCadastroDTO;
import com.guibarao.seguranca_privada.models.Usuarios.TipoUsuario;
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

    @PostMapping("/clientes")
    public ResponseEntity<UsuarioPublicDTO> cadastrarCliente(@RequestBody ClienteCadastroDTO dadosCadastro) {

        UsuarioPublicDTO novoUsuario = usuariosService.cadastrar(dadosCadastro);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PostMapping("/administradores")
    public ResponseEntity<UsuarioPublicDTO> cadastrarAdministrador(@RequestBody FuncionarioCadastroDTO dadosFuncionario) {

        UsuarioPublicDTO novoUsuario = usuariosService.cadastrar(dadosFuncionario, TipoUsuario.ADMINISTRADOR);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PostMapping("/segurancas")
    public ResponseEntity<UsuarioPublicDTO> cadastrarSeguranca(@RequestBody FuncionarioCadastroDTO dadosFuncionario) {

        UsuarioPublicDTO novoUsuario = usuariosService.cadastrar(dadosFuncionario, TipoUsuario.SEGURANCA);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }




}
