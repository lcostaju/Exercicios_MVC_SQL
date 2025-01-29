package br.edu.iftm.tspi.pmvc.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.tspi.pmvc.login.domain.Login;
import br.edu.iftm.tspi.pmvc.login.service.LoginService;

@Controller
public class LoginController {

    private LoginService service;

    
    public LoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping("/")

    public String telaInicial(Model model) {
        return "login/login";
    }

    @PostMapping("/login/entrar")
    public String logar(Login loginDigitado, Model model) {

        if(service.verificaLoginSenha(loginDigitado))
            model.addAttribute("mensagem","Usuario e senha corretos");
        else
            model.addAttribute("mensagem","usuário e senha incorretos");    
        // model.addAttribute("mensagem",
        //         "Você digitou o usuário" + loginDigitado.getUsuario() + " e senha " + loginDigitado.getSenha());

        return "login/login";

    }



}
