package br.edu.iftm.tspi.pmvc.clinica_medica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;


@Controller
public class HomeController {
    
    @GetMapping("/")
    public String index(Model model) {

        return "fragments/layout";
    }
    
}
