package br.edu.iftm.tspi.pmvc.clinica_medica.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.sql.Date;
//import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;

@Repository
public class ConsultRepositoy {

    private final JdbcTemplate conexao;

    public ConsultRepositoy(JdbcTemplate conexao) {
        this.conexao = conexao;
        this.consultas = new ArrayList<>();
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

    private final List<Consulta> consultas;
    public static List<Consulta> consultas2 = new ArrayList<>();

    // Removed default constructor to ensure 'conexao' is always initialized

    public List<Consulta> listar() {
        String sql = """
                select codConsulta as codConsulta,
                       nomeMedico as nomeMedico,
                       dataConsulta as dataConsulta,
                      observacoes as observacoes,
                      tipoConsulta as tipoConsulta,
                      nomePaciente as nomePaciente
                from consulta;
                """;
        return conexao.query(sql,
                new BeanPropertyRowMapper<>(Consulta.class));
    }

    public List<Consulta> listarPorNomeMedico(String nomeMedico) {
        List<Consulta> consultasFiltradas = new ArrayList<>();
        for (Consulta consulta : this.consultas) {
            if (consulta.getNomeMedico().equals(nomeMedico)) {
                consultasFiltradas.add(consulta);
            }
        }
        return consultasFiltradas;
    }

    public Consulta buscaPorCod(Integer codConsulta) {
        String sql = """
                select codConsulta as codConsulta,
                       nomeMedico as nomeMedico,
                       dataConsulta as dataConsulta,
                      observacoes as observacoes,
                      tipoConsulta as tipoConsulta,
                      nomePaciente as nomePaciente
                from consulta
                where
                  codConsulta = ?
                """;
        return conexao.queryForObject(sql,
                new BeanPropertyRowMapper<>(Consulta.class), codConsulta);
    }

    public void novaConsulta(Consulta consulta) {
        String sql = """
                insert into consulta (nomeMedico, dataConsulta, observacoes, tipoConsulta, nomePaciente)
                values (?, ?, ?, ?, ?);
                """;
        conexao.update(sql, consulta.getNomeMedico(), consulta.getDataConsulta(), consulta.getObservacoes(),
                consulta.getTipoConsulta(), consulta.getNomePaciente());
    }

    public void deleteConsulta(Integer codConsulta) {
        String sql = """
                delete from Consulta where codConsulta = ?
                """;
        conexao.update(sql, codConsulta);        
    }

    public void updateConsulta(Consulta consulta) {
        // int index = consultas2.indexOf(consulta);
        // if (index != -1) {
        //     consultas2.set(index, consulta);
        //     return true;
        // }
        // return false;
        String sql = """
                update Consulta
                set nomeMedico = ?,
                dataConsulta = ?,
                observacoes = ?,
                tipoConsulta = ?,
                nomePaciente = ?
                where codConsulta = ?
                """;
                conexao.update(sql, consulta.getNomeMedico(),consulta.getDataConsulta(),consulta.getObservacoes(),consulta.getTipoConsulta(),consulta.getNomePaciente(),consulta.getCodConsulta());
    }

}
