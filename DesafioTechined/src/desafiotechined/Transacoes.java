/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiotechined;

/**
 *
 * @author truck
 */
public class Transacoes {
    private String operacao,dataOperacao,InformacaoAdicional;
    private float valor;

    public Transacoes(String dataOperacao,String operacao, float valor,String InformacaoAdicional) {
        this.operacao = operacao;
        this.dataOperacao = dataOperacao;
        this.InformacaoAdicional = InformacaoAdicional;
        this.valor = valor;
    }
    
    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(String dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getInformacaoAdicional() {
        return InformacaoAdicional;
    }

    public void setInformacaoAdicional(String InformacaoAdicional) {
        this.InformacaoAdicional = InformacaoAdicional;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
