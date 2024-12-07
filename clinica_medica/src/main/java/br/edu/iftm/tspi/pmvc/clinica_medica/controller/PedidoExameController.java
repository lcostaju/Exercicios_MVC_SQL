package br.edu.iftm.tspi.pmvc.clinica_medica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.PedidoExame;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.ConsultRepositoy;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.PedidoExameRepository;

@Controller
@RequestMapping("/pedidoexame")
public class PedidoExameController {
    
    private final PedidoExameRepository pedidoRepositoy;

    public PedidoExameRepository getPedidoRepositoy() {
        return pedidoRepositoy;
    }

    public static final String URL_LISTA = "pedidoExame/lista";
    public static final String URL_FORM = "pedidoExame/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/pedidoexame";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "pedidoExame";
    public static final String ATRIBUTO_LISTA = "pedidosExame";

    public PedidoExameController(PedidoExameRepository pedidoRepositoy) {
        this.pedidoRepositoy = pedidoRepositoy;
    }

    @GetMapping
    public String listar(Model model) {
        List<PedidoExame> pedidosExame = pedidoRepositoy.listar();
        model.addAttribute(ATRIBUTO_LISTA,pedidosExame);
        return URL_LISTA;
    }


    // @GetMapping("/novo/{consulta}")
    // public String novo(Model model,@PathVariable("consulta") Integer codConsulta) {
    //     ConsultRepositoy consultRepositoy = new ConsultRepositoy();
    //     Consulta consulta = consultRepositoy.buscaPorCod(codConsulta);
    //     model.addAttribute(ATRIBUTO_OBJETO, new PedidoExame(consulta));
    //     model.addAttribute("consulta", codConsulta);
    //     return URL_FORM;
    // }

    @GetMapping("/novo/{consulta}")
    public String novo(Model model,@PathVariable("consulta") Integer codConsulta) {
        ConsultRepositoy consultRepositoy = new ConsultRepositoy();
        Consulta consulta = consultRepositoy.buscaPorCod(codConsulta);
        model.addAttribute(ATRIBUTO_OBJETO, new PedidoExame(consulta));
        model.addAttribute("consulta", codConsulta);
        return URL_FORM;
    }


    @PostMapping("/criarexame/{consulta}")
    public String salvar(@ModelAttribute("pedidoExame") PedidoExame pedidoExame) {
        // Integer codConsulta = pedidoExame.getConsulta().getCodConsulta();
        // Consulta consulta = consultaRepository.buscaPorCod(codConsulta);
        // pedidoExame.setConsulta(consulta);
        // Sua lógica de salvar aqui
        return "redirect:/pedidoexame";
    }

@PostMapping("/salvar")
public String salvarPedidoExame(@ModelAttribute PedidoExame pedidoExame, RedirectAttributes redirectAttributes) {
    pedidoRepositoy.novoPedidoExame(pedidoExame);
    return "redirect:/pedidoexame";
}

}
