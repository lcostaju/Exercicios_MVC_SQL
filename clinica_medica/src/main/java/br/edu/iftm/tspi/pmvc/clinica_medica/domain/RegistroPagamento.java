package br.edu.iftm.tspi.pmvc.clinica_medica.domain;

import java.sql.Date;

public class RegistroPagamento {
    private Integer codPagamento;
    private String tipoPagamento;
    private Double valorPagamento;
    private Date dataPagamento;
    private Consulta consulta;
    private String observacao;

    public RegistroPagamento() {
    }

    public RegistroPagamento(Integer codPagamento, String tipoPagamento, Double valorPagamento, Date dataPagamento, Consulta consulta, String observacao) {
        this.codPagamento = codPagamento;
        this.tipoPagamento = tipoPagamento;
        this.valorPagamento = valorPagamento;
        this.dataPagamento = dataPagamento;
        this.consulta = consulta;
        this.observacao = observacao;
    }

    public RegistroPagamento(Consulta consulta2) {
        //TODO Auto-generated constructor stub
        this.consulta = consulta2;
    }

    public RegistroPagamento(Integer codPagamento2) {
        //TODO Auto-generated constructor stub
        this.codPagamento = codPagamento2;
    }

    public Integer getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(Integer codPagamento) {
        this.codPagamento = codPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codPagamento == null) ? 0 : codPagamento.hashCode());
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
        RegistroPagamento other = (RegistroPagamento) obj;
        if (codPagamento == null) {
            if (other.codPagamento != null)
                return false;
        } else if (!codPagamento.equals(other.codPagamento))
            return false;
        return true;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
}
