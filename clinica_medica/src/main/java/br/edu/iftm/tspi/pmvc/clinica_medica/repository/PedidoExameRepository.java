package br.edu.iftm.tspi.pmvc.clinica_medica.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.PedidoExame;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.RegistroPagamento;

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

    private final JdbcTemplate conexao;
    private final List<PedidoExame> pedidosExame;
    //private static ConsultRepositoy consultRepositoy = new ConsultRepositoy();
    //private static Consulta consultaInicial = consultRepositoy.buscaPorCod(1);
    public static List<PedidoExame> pedidosExame2 = new ArrayList<>();

    // static{
    //     pedidosExame2.add(new PedidoExame(1, "Hemograma", stringToDate("03/12/2024"), consultaInicial, "Observação", "Laboratório"));
    // }
    

    public PedidoExameRepository(JdbcTemplate conexao) {
        this.conexao = conexao;
        this.pedidosExame = new ArrayList<>();
        this.pedidosExame.add(
                new PedidoExame(99999, "Hemograma", stringToDate("03/12/2024"), new Consulta(), "Observação", "Laboratório"));
    }

    public List<PedidoExame> listar() {
        String sql = """
                select 
                    p.codExame as codExame,
                    p.nomeExame as nomeExame,
                    p.dataSolicitacao as dataSolicitacao,
                    p.observacao as observacao,
                    p.laboratorio as laboratorio,
                    c.codConsulta as codConsulta,
                    c.nomeMedico as nomeMedico,
                    c.dataConsulta as dataConsulta,
                    c.observacoes as observacoes_consulta,
                    c.tipoConsulta as tipoConsulta,
                    c.nomePaciente as nomePaciente

                from 
                    PedidoExame as p, Consulta as c

                where
                    p.consulta_id = c.codConsulta

                """;

                return conexao.query(sql, (rs,rowNum) -> getPedidoExame(rs));
    }

    public List<PedidoExame> listarPorConsulta(Consulta consulta) {
        // List<PedidoExame> pedidosConsulta = new ArrayList<>();
        // for (PedidoExame pedido : pedidosExame2) {
        //     if (pedido.getConsulta().getCodConsulta().equals(consulta.getCodConsulta())) {
        //         pedidosConsulta.add(pedido);
        //     }
        // }
        // return pedidosConsulta;
        String sql = """
                select 
                    p.codExame as codExame,
                    p.nomeExame as nomeExame,
                    p.dataSolicitacao as dataSolicitacao,
                    p.observacao as observacao,
                    p.laboratorio as laboratorio,
                    c.codConsulta as codConsulta,
                    c.nomeMedico as nomeMedico,
                    c.dataConsulta as dataConsulta,
                    c.observacoes as observacoes_consulta,
                    c.tipoConsulta as tipoConsulta,
                    c.nomePaciente as nomePaciente

                from 
                    Consulta as c, PedidoExame as p

                where
                    p.consulta_id = c.codConsulta and 
                    p.consulta_id = ?

                """;

                return conexao.query(sql, (rs,rowNum) -> getPedidoExame(rs),consulta.getCodConsulta());
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
        String sql = """
                insert into PedidoExame
                    (nomeExame, dataSolicitacao, consulta_id, observacao, laboratorio) values
                    (?,?,?,?,?)
                """;
                conexao.update(sql, pedidoExame.getNomeExame(),pedidoExame.getDataSolicitacao(),pedidoExame.getConsulta().getCodConsulta(),pedidoExame.getObservacao(),pedidoExame.getLaboratorio());
    }

    public void updatePedidoExame(PedidoExame pedidoExame) {
        String sql = """
                update PedidoExame 
                set nomeExame = ?,
                dataSolicitacao = ?,
                observacao = ?,
                laboratorio = ?
                where
                codExame = ?
                """;
            conexao.update(sql, pedidoExame.getNomeExame(),pedidoExame.getDataSolicitacao(),pedidoExame.getObservacao(),pedidoExame.getLaboratorio(),pedidoExame.getCodExame());    
    }

    public PedidoExame buscaPorCod(Integer codPedidoExame) {
        String sql = """
                select 
                    p.codExame as codExame,
                    p.nomeExame as nomeExame,
                    p.dataSolicitacao as dataSolicitacao,
                    p.observacao as observacao,
                    p.laboratorio as laboratorio,
                    c.codConsulta as codConsulta,
                    c.nomeMedico as nomeMedico,
                    c.dataConsulta as dataConsulta,
                    c.observacoes as observacoes_consulta,
                    c.tipoConsulta as tipoConsulta,
                    c.nomePaciente as nomePaciente

                from 
                    Consulta as c, PedidoExame as p

                where
                    p.consulta_id = c.codConsulta and 
                    p.codExame = ?

                """;

             return  conexao.queryForObject(sql, (rs,rowNum) -> getPedidoExame(rs),codPedidoExame);
    }

    public void deletePedidoExame(Integer codPedidoExame) {
        String sql = """
                delete from PedidoExame 
                where codExame = ? 

                """;

                conexao.update(sql, codPedidoExame);
    }

     public static PedidoExame getPedidoExame(ResultSet rs) throws SQLException{
        PedidoExame pedidoExame = new PedidoExame();
        pedidoExame.setCodExame(rs.getInt("codExame"));
        pedidoExame.setNomeExame(rs.getString("nomeExame"));
        pedidoExame.setDataSolicitacao(rs.getDate("dataSolicitacao"));
        pedidoExame.setObservacao(rs.getString("observacao"));
        pedidoExame.setLaboratorio(rs.getString("laboratorio"));

        Consulta consulta = new Consulta();
        consulta.setCodConsulta(rs.getInt("codConsulta"));
        consulta.setNomeMedico(rs.getString("nomeMedico"));
        consulta.setDataConsulta(rs.getDate("dataConsulta"));
        consulta.setObservacoes(rs.getString("observacoes_consulta"));
        consulta.setTipoConsulta(rs.getString("tipoConsulta"));
        consulta.setNomePaciente(rs.getString("nomePaciente"));

        pedidoExame.setConsulta(consulta);

        return pedidoExame;
    }
}
