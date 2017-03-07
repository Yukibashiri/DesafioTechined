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
public class ContaPoupanca extends DadosConta {
    private final float taxa = (float) 1.005;
    public ContaPoupanca(int numeroConta, int agencia, String titular, String dataDeAbertura, float saldo,int tipoConta) {
        super(titular, dataDeAbertura, saldo, numeroConta, agencia, tipoConta);
    }

    //Metodos Novos//
    
    public void aplicarRendimento(){
     setSaldo(getSaldo()*taxa);
    }
    public float getRendimento(){
        return (getSaldo()*taxa - getSaldo());
    }
    
    //Metodo de saque, verifica se a conta possue credito, se sim é sacado e removido o valor do saldo total, se não o saque não é realizado.
    @Override
    public boolean efetuarSaque(float sacar) {
        if ((getSaldo() - sacar) >= 0){
            setSaldo(getSaldo() - sacar);
            return true;
        }else{
            return false;
        }
    }
    // Metodo para deposito, saldo anterior + o valor depositado.
    @Override
    public void efetuarDeposito(float deposito){
        setSaldo(getSaldo()+deposito);
    }
    @Override
    //Poderia ter simplesmente chamado o metodo EfetuarSaque dentro do Metodo efetuarTransferencia, tendo em vista que são a mesma coisa. Entretanto, como o codigo pode passar por mudanças, optei por não fazer isso.
    public boolean efetuarTransferencia(float valor) {
        if ((getSaldo() - valor) >= 0){
            setSaldo(getSaldo() - valor);
        }else{
            return false;
        }
        return true;
    }
    
}
