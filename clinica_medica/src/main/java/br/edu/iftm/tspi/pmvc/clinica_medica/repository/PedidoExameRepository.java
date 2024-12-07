package br.edu.iftm.tspi.pmvc.clinica_medica.repository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.PedidoExame;

@Repository
public class PedidoExameRepository {
    
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

    private final List<PedidoExame> pedidosExame;
    private final ConsultRepositoy consultRepositoy = new ConsultRepositoy();
    private final Consulta consultaInicial = consultRepositoy.buscaPorCod(1);
    public static List<PedidoExame> pedidosExame2 = new ArrayList<>();
    

    public PedidoExameRepository() {
        this.pedidosExame = new ArrayList<>();
        this.pedidosExame.add(
                new PedidoExame(99999, "Hemograma", stringToDate("03/12/2024"), consultaInicial));
                      
    }

    public List<PedidoExame> listar() {
        return pedidosExame2;
    }

    public List<PedidoExame> listarPorConsulta(Consulta consulta) {
        List<PedidoExame> pedidosConsulta = new ArrayList<>();
        for (PedidoExame pedido : pedidosExame2) {
            if (pedido.getConsulta().getCodConsulta().equals(consulta.getCodConsulta())) {
                pedidosConsulta.add(pedido);
            }
        }
        return pedidosConsulta;
    }


    public PedidoExame buscaPorconsuConsulta(Consulta consulta) {
        PedidoExame pedidoBusca = new PedidoExame(consulta);        
        int index = pedidosExame.indexOf(pedidoBusca);
        if (index != -1) {
            return pedidosExame.get(index);
        } else {
            return null; 
        }
    }

    public void novoPedidoExame(PedidoExame pedidoExame) {
        int cod = pedidosExame2.size()+ 1;
        pedidoExame.setCodExame(cod);
        pedidosExame2.add(pedidoExame);
        //return this.consultas;
    }
}
