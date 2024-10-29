package com.sistema.clientes.sistema_clientes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.sistema.clientes.sistema_clientes.domain.Cliente;
import com.sistema.clientes.sistema_clientes.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ClienteController {
    
    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clientes")
    public String getClientes(Model model) {
        model.addAttribute("clientes",repository.getClientes());
        return "clientesView";
    }

    @GetMapping("/add")
    public String exibirFormularioAdd(Model model) {
        model.addAttribute("cliente",new Cliente());
        return "clienteAdd";
    }

    @PostMapping("/add")
    public String add(Model model,@ModelAttribute Cliente cliente) {
        //TODO: process POST request
        repository.addCliente(cliente);
        
        return getClientes(model);
    }
    
    
    
}
