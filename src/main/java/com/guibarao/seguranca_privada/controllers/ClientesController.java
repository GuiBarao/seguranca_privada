package com.guibarao.seguranca_privada.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.AutoWired;
import com.guibarao.seguranca_privada.services.ClientesService;
import org.springframework.web.annotation.GetMapping;

@RestController
@RequestMapping("/clientes")
public class ClientesController{

    @Autowired
    private final ClientesServices service;

    @GetMapping
    public List<ClientePublicDTO> getClientes() {

    }

}