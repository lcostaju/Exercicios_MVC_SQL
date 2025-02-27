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
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/pedidoexame")
public class PedidoExameController {
    
    private final PedidoExameRepository pedidoRepositoy;
    private final ConsultRepositoy consultRepositoy;

    public PedidoExameRepository getPedidoRepositoy() {
        return pedidoRepositoy;
    }

    public static final String URL_LISTA = "pedidoExame/lista";
    public static final String URL_FORM = "pedidoExame/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/pedidoexame";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "pedidoExame";
    public static final String ATRIBUTO_LISTA = "pedidosExame";

    public PedidoExameController(PedidoExameRepository pedidoRepositoy, ConsultRepositoy consultRepositoy) {
        this.pedidoRepositoy = pedidoRepositoy;
        this.consultRepositoy = consultRepositoy;
    }

    @GetMapping
    public String listar(Model model) {
        List<PedidoExame> pedidosExame = pedidoRepositoy.listar();
        model.addAttribute(ATRIBUTO_LISTA,pedidosExame);
        return URL_LISTA;
    }


    @GetMapping("/novo/{consulta}")
    public String novo(Model model,@PathVariable("consulta") Integer codConsulta) {
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

@GetMapping("/editar/{codPedidoExame}")
public String abrirFormEditar(@PathVariable Integer codPedidoExame, Model model, RedirectAttributes redirectAttributes) {
    PedidoExame pedidoExame = pedidoRepositoy.buscaPorCod(codPedidoExame);

    if (pedidoExame == null) {
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, codPedidoExame+" não encontrado.");
        return URL_REDIRECT_LISTA;
    } else {
        model.addAttribute(ATRIBUTO_OBJETO,pedidoExame);
        return URL_FORM; 
    }    

}

@PostMapping("/editar/{codPedidoExame}")
    public String atualizar(@PathVariable("codPedidoExame") Integer codPedidoExame, @ModelAttribute("codPedidoExame") PedidoExame pedidoExame, RedirectAttributes redirectAttributes) {
       pedidoRepositoy.updatePedidoExame(pedidoExame);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Atualizado com sucesso");
              
        return URL_REDIRECT_LISTA;
    }

@PostMapping("/delete/{codPedidoExame}")
public String postMethodName(@PathVariable Integer codPedidoExame, RedirectAttributes redirectAttributes) {
    pedidoRepositoy.deletePedidoExame(codPedidoExame);
    redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Pedido de exame excluído com sucesso.");
    
    return URL_REDIRECT_LISTA;
}
    

}
