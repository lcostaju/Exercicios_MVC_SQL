package br.edu.iftm.tspi.pmvc.clinica_medica.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.PedidoExame;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.RegistroPagamento;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class PagamentoRepository {

    private final JdbcTemplate conexao;
    
    public PagamentoRepository(JdbcTemplate conexao) {
        this.conexao = conexao;
        this.pagamentos = new ArrayList<>();
    }
    
    

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
    // private static ConsultRepositoy consultRepositoy = new ConsultRepositoy();
    // private static Consulta consultaInicial = consultRepositoy.buscaPorCod(1);
    public static List<RegistroPagamento> pagamentos2 = new ArrayList<>();

    static{
        pagamentos2.add(new RegistroPagamento(1,"Dinheiro", 100.0, stringToDate("03/12/2024"), new Consulta(), "observations"));
    }
    


    public List<RegistroPagamento> listar() {
        // String sql = """
        //               select codPagamento as codPagamento,
        //                      tipoPagamento as tipoPagamento,
        //                      valorPagamento as valorPagamento,
        //                      dataPagamento as dataPagamento,
        //                      consulta_id as consulta_id,
        //                      observacao as observacao
        //               from RegistroPagamento;
        //               """;
        String sql = """
                select 
                    p.codPagamento as codPagamento,
                    p.dataPagamento as dataPagamento,
                    p.observacao as observacao,
                    p.tipoPagamento as tipoPagamento,
                    p.valorPagamento as valorPagamento,
                    c.codConsulta as codConsulta,
                    c.nomeMedico as nomeMedico,
                    c.dataConsulta as dataConsulta,
                    c.observacoes as observacoes_consulta,
                    c.tipoConsulta as tipoConsulta,
                    c.nomePaciente as nomePaciente

                from 
                    Consulta as c, RegistroPagamento as p

                where
                    p.consulta_id = c.codConsulta

                """;
        return conexao.query(sql, (rs,rowNum) -> getRegistroPagamento(rs));
    }

    public List<RegistroPagamento> listarPorConsulta(Consulta consulta) {
        // List<RegistroPagamento> pedidosConsulta = new ArrayList<>();
        // for (RegistroPagamento pedido : pagamentos2) {
        //     if (pedido.getConsulta().getCodConsulta().equals(consulta.getCodConsulta())) {
        //         pedidosConsulta.add(pedido);
        //     }
        // }
        // return pedidosConsulta;
        String sql = """
                select 
                    p.codPagamento as codPagamento,
                    p.dataPagamento as dataPagamento,
                    p.observacao as observacao,
                    p.tipoPagamento as tipoPagamento,
                    p.valorPagamento as valorPagamento,
                    c.codConsulta as codConsulta,
                    c.nomeMedico as nomeMedico,
                    c.dataConsulta as dataConsulta,
                    c.observacoes as observacoes_consulta,
                    c.tipoConsulta as tipoConsulta,
                    c.nomePaciente as nomePaciente

                from 
                    Consulta as c, RegistroPagamento as p

                where
                    p.consulta_id = c.codConsulta and 
                    p.consulta_id = ?

                """;

                return conexao.query(sql, (rs,rowNum) -> getRegistroPagamento(rs),consulta.getCodConsulta());
    }


    // public RegistroPagamento buscaPorconsuConsulta(Consulta consulta) {
    //     RegistroPagamento pedidoBusca = new RegistroPagamento(consulta);        
    //     int index = pagamentos.indexOf(pedidoBusca);
    //     if (index != -1) {
    //         return pagamentos.get(index);
    //     } else {
    //         return null; 
    //     }
    // }

    public void novoPagamento(RegistroPagamento pagamento) {
        String sql = """
                insert into RegistroPagamento (tipoPagamento,valorPagamento,dataPagamento,consulta_id,observacao)
                values(?,?,?,?,?)
                """;
                conexao.update(sql, pagamento.getTipoPagamento(),pagamento.getValorPagamento(),pagamento.getDataPagamento(),pagamento.getConsulta().getCodConsulta(),pagamento.getObservacao());
    }

    public void updatePagamento(RegistroPagamento pagamento) {
        String sql = """
                update RegistroPagamento
                set tipoPagamento = ?,
                 valorPagamento = ?,
                 dataPagamento = ?,
                 observacao = ?
                where codPagamento = ?
                """;

                conexao.update(sql, pagamento.getTipoPagamento(),pagamento.getValorPagamento(),pagamento.getDataPagamento(),pagamento.getObservacao(),pagamento.getCodPagamento());
    }

    public RegistroPagamento buscaPorCod(Integer codPagamento) {
        String sql = """
                select 
                    p.codPagamento as codPagamento,
                    p.dataPagamento as dataPagamento,
                    p.observacao as observacao,
                    p.tipoPagamento as tipoPagamento,
                    p.valorPagamento as valorPagamento,
                    c.codConsulta as codConsulta,
                    c.nomeMedico as nomeMedico,
                    c.dataConsulta as dataConsulta,
                    c.observacoes as observacoes_consulta,
                    c.tipoConsulta as tipoConsulta,
                    c.nomePaciente as nomePaciente

                from 
                    Consulta as c, RegistroPagamento as p

                where
                    p.consulta_id = c.codConsulta and 
                    p.codPagamento = ?

                """;

                return conexao.queryForObject(sql, (rs,rowNum) -> getRegistroPagamento(rs),codPagamento);
    }

    public void deletePagamento(Integer codPagamento) {
        String sql = """
                delete from RegistroPagamento where codPagamento = ?
                """;
                conexao.update(sql, codPagamento);
    }

    public static RegistroPagamento getRegistroPagamento(ResultSet rs) throws SQLException{
        RegistroPagamento pagamento = new RegistroPagamento();
        pagamento.setCodPagamento(rs.getInt("codPagamento"));
        pagamento.setDataPagamento(rs.getDate("dataPagamento"));
        pagamento.setObservacao(rs.getString("observacao"));
        pagamento.setTipoPagamento(rs.getString("tipoPagamento"));
        pagamento.setValorPagamento(rs.getDouble("valorPagamento"));

        Consulta consulta = new Consulta();
        consulta.setCodConsulta(rs.getInt("codConsulta"));
        consulta.setNomeMedico(rs.getString("nomeMedico"));
        consulta.setDataConsulta(rs.getDate("dataConsulta"));
        consulta.setObservacoes(rs.getString("observacoes_consulta"));
        consulta.setTipoConsulta(rs.getString("tipoConsulta"));
        consulta.setNomePaciente(rs.getString("nomePaciente"));

        pagamento.setConsulta(consulta);

        return pagamento;
    }
}
