package br.edu.iftm.tspi.pmvc.clinica_medica.domain;

import java.sql.Date;

public class PedidoExame {
    private Integer codExame;
    private String nomeExame;
    private Date dataSolicitacao;
    private Consulta consulta;
    private String observacao;
    private String laboratorio;

    public PedidoExame() {
    }

    public PedidoExame(Integer codExame, String nomeExame, Date dataSolicitacao,Consulta consulta, String observacao, String laboratorio) {
        this.codExame = codExame;
        this.nomeExame = nomeExame;
        this.dataSolicitacao = dataSolicitacao;
        this.consulta = consulta;
        this.observacao = observacao;
        this.laboratorio = laboratorio;
    }

    public PedidoExame(Integer codExame, String nomeExame, Date dataSolicitacao,String observacao, String laboratorio) {
        this.codExame = codExame;
        this.nomeExame = nomeExame;
        this.dataSolicitacao = dataSolicitacao;
        this.observacao = observacao;
        this.laboratorio = laboratorio;
    }
    public PedidoExame(Consulta consulta2) {
        //TODO Auto-generated constructor stub
        consulta = consulta2;
    }

    public PedidoExame(Integer codPedidoExame) {
        //TODO Auto-generated constructor stub
        this.codExame = codPedidoExame;
    }

    public Integer getCodExame() {
        return codExame;
    }

    public void setCodExame(Integer codExame) {
        this.codExame = codExame;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codExame == null) ? 0 : codExame.hashCode());
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
        PedidoExame other = (PedidoExame) obj;
        if (codExame == null) {
            if (other.codExame != null)
                return false;
        } else if (!codExame.equals(other.codExame))
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

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    
}
