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
public abstract class DadosConta {

    private String  titular, dataDeAbertura;
    private float saldo;
    private int contaTipo,numeroConta, agencia;

    public DadosConta(String titular, String dataDeAbertura, float saldo, int numeroConta, int agencia,int contaTipo) {
        this.titular = titular;
        this.dataDeAbertura = dataDeAbertura;
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.contaTipo = contaTipo;
    }

// Getters e Setters //
    public int getContaTipo() {
        return contaTipo;
    }

    public void setContaTipo(int contaTipo) {
        this.contaTipo = contaTipo;
    }
    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }



    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getDataDeAbertura() {
        return dataDeAbertura;
    }

    public void setDataDeAbertura(String dataDeAbertura) {
        this.dataDeAbertura = dataDeAbertura;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    // Metodos de Operações  //
    
    public abstract boolean efetuarSaque(float valor);

    public abstract void efetuarDeposito(float deposito);

    public abstract boolean efetuarTransferencia(float valor);
}
