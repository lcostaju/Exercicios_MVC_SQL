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
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.RegistroPagamento;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.ConsultRepositoy;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.PagamentoRepository;
import br.edu.iftm.tspi.pmvc.clinica_medica.repository.PedidoExameRepository;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
    
    private final PagamentoRepository pagamentoRepository;

    public PagamentoRepository getPagamentoRepository() {
        return pagamentoRepository;
    }

    public static final String URL_LISTA = "pagamento/lista";
    public static final String URL_FORM = "pagamento/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/pagamento";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "pagamento";
    public static final String ATRIBUTO_LISTA = "pagamentos";

    public PagamentoController(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        List<RegistroPagamento> pagamentos = pagamentoRepository.listar();
        model.addAttribute(ATRIBUTO_LISTA,pagamentos);
        return URL_LISTA;
    }


    @GetMapping("/novo/{consulta}")
    public String novo(Model model,@PathVariable("consulta") Integer codConsulta) {
        ConsultRepositoy consultRepositoy = new ConsultRepositoy();
        Consulta consulta = consultRepositoy.buscaPorCod(codConsulta);
        model.addAttribute(ATRIBUTO_OBJETO, new RegistroPagamento(consulta));
        model.addAttribute("consulta", codConsulta);
        return URL_FORM;
    }


@PostMapping("/salvar")
public String salvarPagamento(@ModelAttribute RegistroPagamento pagamento, RedirectAttributes redirectAttributes) {
    pagamentoRepository.novoPagamento(pagamento);
    return URL_REDIRECT_LISTA;
}

@GetMapping("/editar/{codPagamento}")
public String abrirFormEditar(@PathVariable Integer codPagamento, Model model, RedirectAttributes redirectAttributes) {
    RegistroPagamento pagamento = pagamentoRepository.buscaPorCod(codPagamento);

    if (pagamento == null) {
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, codPagamento+" não encontrado.");
        return URL_REDIRECT_LISTA;
    } else {
        model.addAttribute(ATRIBUTO_OBJETO,pagamento);
        return URL_FORM; 
    }    

}

@PostMapping("/editar/{codPagamento}")
    public String atualizar(@PathVariable("codPagamento") Integer codPagamento, @ModelAttribute("codPagamento") RegistroPagamento pagamento, RedirectAttributes redirectAttributes) {
        if (pagamentoRepository.updatePagamento(pagamento)) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, pagamento.getCodPagamento()+ " atualizado com sucesso");
        } else {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, " Não foi possível atualizar "+pagamento.getCodPagamento());
        }        
        return URL_REDIRECT_LISTA; 
    }

@PostMapping("/delete/{codPagamento}")
public String postMethodName(@PathVariable Integer codPagamento, RedirectAttributes redirectAttributes) {
    pagamentoRepository.deletePagamento(codPagamento);
    redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Pagamento excluído com sucesso.");
    
    return URL_REDIRECT_LISTA;
}
    

}
