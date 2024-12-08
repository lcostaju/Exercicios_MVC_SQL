package br.edu.iftm.tspi.pmvc.clinica_medica.repository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.PedidoExame;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.RegistroPagamento;

@Repository
public class PagamentoRepository {
    
    public static Date stringToDate(String dataEmTexto) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Converte a string para java.util.Date
            java.util.Date utilDate = sdf.parse(dataEmTexto);

            // Converte java.util.Date para java.sql.Date
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            // Exibe mensagem de erro e retorna null caso falhe
            System.out.println("Erro ao converter a string para Date: " + e.getMessage());
            return null;
        }
    }

    private final List<RegistroPagamento> pagamentos;
    private static ConsultRepositoy consultRepositoy = new ConsultRepositoy();
    private static Consulta consultaInicial = consultRepositoy.buscaPorCod(1);
    public static List<RegistroPagamento> pagamentos2 = new ArrayList<>();

    static{
        pagamentos2.add(new RegistroPagamento(1,"Dinheiro", 100.0, stringToDate("03/12/2024"), consultaInicial));
    }
    

    public PagamentoRepository() {
        this.pagamentos = new ArrayList<>();
        this.pagamentos.add(new RegistroPagamento(99999, "Dinheiro", 100.0, stringToDate("03/12/2024"), consultaInicial));
    }

    public List<RegistroPagamento> listar() {
        return pagamentos2;
    }

    public List<RegistroPagamento> listarPorConsulta(Consulta consulta) {
        List<RegistroPagamento> pedidosConsulta = new ArrayList<>();
        for (RegistroPagamento pedido : pagamentos2) {
            if (pedido.getConsulta().getCodConsulta().equals(consulta.getCodConsulta())) {
                pedidosConsulta.add(pedido);
            }
        }
        return pedidosConsulta;
    }


    public RegistroPagamento buscaPorconsuConsulta(Consulta consulta) {
        RegistroPagamento pedidoBusca = new RegistroPagamento(consulta);        
        int index = pagamentos.indexOf(pedidoBusca);
        if (index != -1) {
            return pagamentos.get(index);
        } else {
            return null; 
        }
    }

    public void novoPagamento(RegistroPagamento pagamento) {
        //int cod = pagamentos2.size()+ 1;
        int cod = pagamentos2.get(pagamentos2.size()-1).getCodPagamento() + 1;
        pagamento.setCodPagamento(cod);
        pagamentos2.add(pagamento);
        //return this.consultas;
    }

    public boolean updatePagamento(RegistroPagamento pagamento) {
        int index = pagamentos2.indexOf(pagamento);
        if (index != -1) {
            pagamentos2.set(index, pagamento);
            return true;
        }
        return false;
    }

    public RegistroPagamento buscaPorCod(Integer codPagamento) {
        RegistroPagamento pagamento = new RegistroPagamento(codPagamento);        
        int index = pagamentos2.indexOf(pagamento);
        if (index != -1) {
            return pagamentos2.get(index);
        } else {
            return null; 
        }
    }

    public boolean deletePagamento(Integer codPagamento) {
        RegistroPagamento pagamento = new RegistroPagamento(codPagamento);
        return pagamentos2.remove(pagamento);
    }
}
