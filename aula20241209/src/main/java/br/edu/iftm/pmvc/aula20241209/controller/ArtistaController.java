package br.edu.iftm.pmvc.aula20241209.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import br.edu.iftm.pmvc.aula20241209.domain.Artista;
import br.edu.iftm.pmvc.aula20241209.repository.ArtistaRepository;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ArtistaController {
    
    private ArtistaRepository repository;

    public ArtistaController(ArtistaRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/artista/listar")
    public String listar(Model model) {
        List<Artista> artistas = repository.listar();
        model.addAttribute("artistas", artistas);
        return "artistas/artistasList";
    }
    
    @GetMapping("/artista/novo")
    public String inserir(Model model) {
        Artista artista = new Artista();
        model.addAttribute("artista", artista);
        return "artistas/artistaForm";
    }

    @PostMapping("/artistas/save")
    public String salvar(@ModelAttribute Artista artista) {
        repository.inserir(artista);
        
        return "redirect:/artista/listar";
    }
    
}
