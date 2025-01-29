package com.example.lombok.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.lombok.domain.Contato; // Ensure this path is correct or update it to the correct package

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContatoController {
    
    @GetMapping("/contatos")
    public String getContatos( Model model) {
        List<Contato> contatos = new ArrayList<>();
        contatos.add(new Contato("Don Ramon", 1));
        contatos.add(new Contato("El Chavo del Ocho", 2));
        model.addAttribute("contatos", contatos);

        return "pagina";
    }
    
}
