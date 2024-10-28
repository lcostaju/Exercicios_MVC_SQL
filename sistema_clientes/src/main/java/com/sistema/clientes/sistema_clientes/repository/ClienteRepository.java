package com.sistema.clientes.sistema_clientes.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sistema.clientes.sistema_clientes.domain.Cliente;


@Component
public class ClienteRepository {
    
    private List<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Lucio", "Rua das flores"));
        clientes.add(new Cliente(2, "Marco", "Rua das couves"));
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
