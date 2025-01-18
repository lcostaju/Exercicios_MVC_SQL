package br.edu.iftm.tspi.pmvc.clinica_medica.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.PedidoExame;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.RegistroPagamento;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.ConsultRepositoy;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.PagamentoRepository;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.PedidoExameRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping ("/consulta")
public class ConsultaController {
    
    private final ConsultRepositoy consultRepositoy;
    private final PagamentoRepository pagamentoRepository;
    private final PedidoExameRepository pedidoExameRepository;

    public ConsultRepositoy getConsultRepositoy() {
        return consultRepositoy;
    }



    public static final String URL_LISTA = "consulta/lista";
    public static final String URL_FORM = "consulta/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/consulta";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "consulta";
    public static final String ATRIBUTO_LISTA = "consultas";

    public ConsultaController(ConsultRepositoy consultRepositoy, PagamentoRepository pagamentoRepository, PedidoExameRepository pedidoExameRepository) {
        this.consultRepositoy = consultRepositoy;
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoExameRepository = pedidoExameRepository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Consulta> consultas = consultRepositoy.listar();
        model.addAttribute(ATRIBUTO_LISTA,consultas);
        return URL_LISTA;
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute(ATRIBUTO_OBJETO, new Consulta());
        return URL_FORM;
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("consulta") Consulta consulta, RedirectAttributes redirectAttributes) {
        consultRepositoy.novaConsulta(consulta);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM,"Consulta salva com sucesso");
        return URL_REDIRECT_LISTA; 
    }


    @GetMapping("/editar/{codConsulta}")
    public String abrirFormEditar(@PathVariable("codConsulta") Integer codConsulta, Model model, RedirectAttributes redirectAttributes) {
        
        Consulta consultaBusca = consultRepositoy.buscaPorCod(codConsulta);   
        //
        
        List<PedidoExame> pedidosExame = pedidoExameRepository.listarPorConsulta(consultaBusca);
        List<RegistroPagamento> pagamentos = pagamentoRepository.listarPorConsulta(consultaBusca);
        //      
        if (consultaBusca == null) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, codConsulta+" não encontrado.");
            return URL_REDIRECT_LISTA;
        } else {
            model.addAttribute(ATRIBUTO_OBJETO,consultaBusca);
            model.addAttribute("pedidosExame", pedidosExame);
            model.addAttribute("pagamentos", pagamentos);
            return URL_FORM; 
        }        
    }

    @PostMapping("/editar/{codConsulta}")
    public String atualizar(@PathVariable("codConsulta") String codConsulta, @ModelAttribute("codConsulta") Consulta consulta, RedirectAttributes redirectAttributes) {
        consultRepositoy.updateConsulta(consulta);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM,"Consulta atualizado com sucesso");
        return URL_REDIRECT_LISTA; 
    }


    @PostMapping("/excluir/{codConsulta}")
    public String excluir(@PathVariable("codConsulta") Integer codConsulta, RedirectAttributes redirectAttributes) {
        consultRepositoy.deleteConsulta(codConsulta); 
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Consulta excluída com sucesso.");
        return URL_REDIRECT_LISTA; 
    }
}
