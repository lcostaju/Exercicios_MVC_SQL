package br.edu.iftm.tspi.pmvc.clinica_medica.domain;

import java.sql.Date;
import java.time.LocalDateTime;

public class Consulta{
    private Integer codConsulta;
    private String nomeMedico; //Será substituido no projeto pela classe medico
    private Date dataConsulta;
    private String observacoes;
    private String tipoConsulta;
    private String nomePaciente; //sera substituido no projeto pela classe paciente
    public static int cont;

    public Consulta() {
        //this.codConsulta = cont++;
    }

    public Consulta(Integer codConsulta,String nomeMedico, Date dataConsulta, String observacoes, String tipoConsulta, String nomePaciente) {
        this.codConsulta = codConsulta;
        this.nomeMedico = nomeMedico;
        this.dataConsulta = dataConsulta;
        this.observacoes = observacoes;
        this.tipoConsulta = tipoConsulta;
        this.nomePaciente = nomePaciente;
    }

    public Consulta(String nomeMedico, Date dataConsulta, String observacoes, String tipoConsulta) {
        this.nomeMedico = nomeMedico;
        this.dataConsulta = dataConsulta;
        this.observacoes = observacoes;
        this.tipoConsulta = tipoConsulta;
    }

    public Consulta(Integer codConsulta2) {
        //TODO Auto-generated constructor stub
        this.codConsulta = codConsulta2;
    }

    public Integer getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(Integer codConsulta) {
        this.codConsulta = codConsulta;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codConsulta == null) ? 0 : codConsulta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Consulta other = (Consulta) obj;
        if (codConsulta == null) {
            if (other.codConsulta != null)
                return false;
        } else if (!codConsulta.equals(other.codConsulta))
            return false;
        return true;
    }


}