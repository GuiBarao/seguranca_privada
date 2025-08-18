package com.guibarao.seguranca_privada.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.guibarao.seguranca_privada.services.ClientesService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/clientes")
public class ClientesController{

    @Autowired
    private ClientesService service;

    @GetMapping
    public String getClientes() {
        return "cliente";
    }

}