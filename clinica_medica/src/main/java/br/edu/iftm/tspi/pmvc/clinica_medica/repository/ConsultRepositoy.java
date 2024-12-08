package br.edu.iftm.tspi.pmvc.clinica_medica.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.sql.Date;
//import java.util.Date;

import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.clinica_medica.domain.Consulta;

@Repository
public class ConsultRepositoy {

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
    public static Consulta consultaInicial = new Consulta(1, "Dr. João", stringToDate("03/12/2024"), "Consulta de rotina", "Consulta", "Maria");

    static {
        consultas2.add(consultaInicial);
    }

    public ConsultRepositoy() {
        this.consultas = new ArrayList<>();
        this.consultas.add(
                new Consulta(99999, "Dr. João", stringToDate("03/12/2024"), "Consulta de rotina", "Consulta", "Maria"));
    }

    public List<Consulta> listar() {
        return consultas2;
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
        Consulta ConsultaBusca = new Consulta(codConsulta);        
        int index = consultas2.indexOf(ConsultaBusca);
        if (index != -1) {
            return consultas2.get(index);
        } else {
            return null; 
        }
    }

    public static void novaConsulta(Consulta consulta) {
        //int cod = consultas2.size() + 1;
        int cod = consultas2.get(consultas2.size()-1).getCodConsulta() + 1;
        consulta.setCodConsulta(cod);
        consultas2.add(consulta);
        //return this.consultas;
    }

    public boolean deleteConsulta(Integer codConsulta) {
        Consulta consulta = new Consulta(codConsulta);
        return consultas2.remove(consulta);
    }

    public boolean updateConsulta(Consulta consulta){
        int index = consultas2.indexOf(consulta);
        if (index != -1) {
            consultas2.set(index, consulta);
            return true;
        }
        return false;
    }

    
}

