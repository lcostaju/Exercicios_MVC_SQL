package br.edu.iftm.tspi.pmvc.aula20241104.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import br.edu.iftm.tspi.pmvc.aula20241104.domain.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {

    @GetMapping("/")
    public String init(Model model) {
        String nomeUsuario = "Carlos Eduardo";
        Usuario usuario = new Usuario();
        usuario.setNome(nomeUsuario);
        usuario.setAdministrador(true);
        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("usuario", usuario);
        List<String> nomes = Arrays.asList("Carlos", "Marco", "Amanda", "Maria");
        model.addAttribute("nomes", nomes);

        //
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Carlos", "carlos123", "senha123", true),
                new Usuario("Maria", "maria456", "senha456", false),
                new Usuario("Joao", "joao789", "senha789", false));
        model.addAttribute("usuarios", usuarios);
        //

        return "usuarioView";
    }

    @GetMapping("/editar/{login}")
    public String mostrarFormularioEdicao(@PathVariable String login, Model model) {
        model.addAttribute("nomeUsuario", login);
        model.addAttribute("nomes", new ArrayList<>());
        model.addAttribute("usuarios", new ArrayList<>());
        model.addAttribute("usuario", new Usuario());
        return "usuarioView";
    }

    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        Usuario usuario = new Usuario();
        usuario.setLogin("amigao");
        usuario.setSenha("teste");
        usuario.setNome("Amigao ESPN");
        usuario.setAdministrador(true);
        model.addAttribute("usuario", usuario);
        return "formUsuario";
    }

    @GetMapping("/vazio")
    public String usuarioVazio(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "formUsuario";
    }
    
    
    
}
