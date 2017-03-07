/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiotechined;

/**
 *
 * @author Yukibashiri
 */
public class ContaCorrente extends DadosConta{
    private float limiteCreditoEspecial,creditoEspecialUtilizado;
 
    public ContaCorrente(int numeroConta, int agencia, String titular, String dataDeAbertura, float saldo,float limiteCreditoEspecial, float creditoEspecialUtilizado,int tipoConta) {
        super(titular,dataDeAbertura,saldo,numeroConta,agencia,tipoConta);
        this.limiteCreditoEspecial = limiteCreditoEspecial;
        this.creditoEspecialUtilizado = creditoEspecialUtilizado;
    }
    
    //Setters e Getters //
    public float getLimiteCreditoEspecial() {
        return limiteCreditoEspecial;
    }

    public void setLimiteCreditoEspecial(float limiteCreditoEspecial) {
        this.limiteCreditoEspecial = limiteCreditoEspecial;
    }

    public float getCreditoEspecialUtilizado() {
        return creditoEspecialUtilizado;
    }

    public void setCreditoEspecialUtilizado(float creditoEspecialUtilizado) {
        this.creditoEspecialUtilizado = creditoEspecialUtilizado;
    }

    //Metodos Novos //
    //Calcula quando do credito especial ainda esta disponivel.
    public float verCreditoEspecial(){
        return (limiteCreditoEspecial - creditoEspecialUtilizado);
    }
    
    // Implementação dos Metodos abstratos herdados //
    
    
    //Efetuar saque verifica se o usuario possui saldo, se o valor for maior que o saldo, então se verifica o 
    //creditoespecial que esse possue, por final caso ele não tenha saldo ou esse esteja negativo, verifica-se o limite de credito que ele ainda pode utilizar, se nenhuma das opções satisfazer o saque é cancelado.
    @Override
    public boolean efetuarSaque(float sacar) {
        if ((getSaldo() - sacar) >= 0){
            setSaldo(getSaldo() - sacar);
        }else 
            if ( (getSaldo() >=0) && (((getSaldo() + verCreditoEspecial())- sacar)>= 0) ){
                sacar -= getSaldo();
                setSaldo(0);
                creditoEspecialUtilizado += sacar;
            }else 
                if( (verCreditoEspecial()- sacar)>= 0 ){
                    creditoEspecialUtilizado += sacar;
                    
                }else{
                    return false;
                }
        return true;
    }
    // Efetuar deposito analisa se o usuario tem algum saldo devedor pela utilização do credito especial. Por não conter nenhuma penalidade em sua utilização sua resolução é bem simples.
    @Override
    public void efetuarDeposito(float deposito){
        if(getSaldo() <= 0 && creditoEspecialUtilizado > 0){
            if ((deposito -  creditoEspecialUtilizado) > 0){
                deposito -=  creditoEspecialUtilizado;
                creditoEspecialUtilizado = 0;
            }else{
             creditoEspecialUtilizado -= deposito;
             deposito = 0;
            }
        }
        setSaldo(getSaldo()+deposito);
    }
    
    // Pelo meu entendimento do problema, o usuario não pode tranferir utilizando o credito especial, já que na problematica não tinha nada explicito dizendo sobre isso.
    // Mas deixo claro aqui que, seria simples de resolver o problema. O codigo ficaria bem similar ao efetuarSaque, contudo uma nova linha deveria ser adicionada nos outros 2 If's do metodo
    // Sendo essa: setSaldo(getSaldo() - (valor * taxa)). Tendo em vista que, mesmo vc utilizando o credito especial a taxa seria cobrada diretamente do seu saldo (podendo assim, negativa-lo) e não do seu credito especial.
    @Override
    public boolean efetuarTransferencia(float valor) {
        if ((getSaldo() - valor) >= 0){ // Não é necessario calcular o valor com a taxa de transferencia, pois a taxa é cobrada após a transferencia, logo o saldo pode sim ficar negativo.
            float taxa = (float) 1.03; // Variavel taxa para facilitar entendimento e facil manutenção caso o taxa de transferencia aumente.
            setSaldo(getSaldo() - (valor * taxa));
        }else{
        return false;
        } 
        return true;
    }
    
}
