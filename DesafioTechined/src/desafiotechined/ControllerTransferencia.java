/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiotechined;

import static desafiotechined.ControllerTelaLogin.usuarioAtual;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yukibashiri
 */
public class ControllerTransferencia implements Initializable {

    Connection con;
    @FXML TextField tfConta;
    @FXML TextField tfAgencia;
    @FXML TextField tfValor;

    
    
    @FXML public void limpar(){
        tfConta.setText("");
        tfAgencia.setText("");
        tfValor.setText("");
    }
    
    @FXML public void voltar(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLMenuOperacoes.fxml", "Menu de Operações");
    } 
   
    @FXML public void efetuarTransferencia(){
        String sql = "select * from verificarlogin where numeroConta = '"+usuarioAtual.getConta()+"';"; 
        String query  = "select * from verificarlogin where numeroConta = '"+tfConta.getText()+"' and agencia = '"+tfAgencia.getText()+"';";  
        try{
            Statement stat = con.createStatement();
            Statement stat2 = con.createStatement();
            ResultSet rs = stat.executeQuery(sql); 
            ResultSet rsq = stat2.executeQuery (query);
            if (rs.next() && rsq.next()){ 
                String destinatario = "Transferido para: Conta: "+rsq.getInt("numeroConta")+", AG: "+rsq.getInt("agencia")+"";
                String remetente = "Transferido por: Conta: "+rs.getInt("numeroConta")+", AG: "+rs.getInt("agencia")+"";
                Alert validar = new Alert(Alert.AlertType.CONFIRMATION);
                validar.setTitle("Confirmar Transferencia");
                validar.setHeaderText("Titular: "+rsq.getString("titular")+". Conta: "+rsq.getInt("numeroConta")+". AG: "+rsq.getInt("agencia"));
                validar.setContentText("Você deseja efetuar a transferencia, no valor de R$: "+tfValor.getText()+" ?");
                Optional<ButtonType> resposta = validar.showAndWait();
                Alert resultado = new Alert(Alert.AlertType.INFORMATION);
                if (resposta.get() == ButtonType.OK){
                    String efetuarTransferencia = "";
                    boolean checarSaldo = false;
                    switch(usuarioAtual.getTipo()){
                        case 1:
                           ContaPoupanca cp = new ContaPoupanca(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getInt(8));
                           checarSaldo = cp.efetuarTransferencia(Float.parseFloat(tfValor.getText()));
                           efetuarTransferencia = "call sp_realizarOperacaoCP('"+cp.getNumeroConta()+"','"+tfValor.getText()+"','"+cp.getSaldo()+"','3','"+destinatario+"');";
                           break;
                        case 2:
                            ContaCorrente cc = new ContaCorrente(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8));
                            checarSaldo = cc.efetuarTransferencia(Float.parseFloat(tfValor.getText()));
                            efetuarTransferencia = "call sp_realizarOperacaoCC('"+cc.getNumeroConta()+"','"+tfValor.getText()+"','"+cc.getSaldo()+"','"+cc.getCreditoEspecialUtilizado()+"','3','"+destinatario+"');";
                            break;
                    }
                    if (checarSaldo == true){
                        stat.executeQuery(efetuarTransferencia);
                        finalizarTransferencia(remetente,tfValor.getText(),rsq.getInt("numeroConta"));
                        resultado.setHeaderText("Transferencia efetuada com sucesso!");
                    }else{
                        resultado.setHeaderText("Transferencia cancelada, fundo insuficiente!");
                    }
                } else {
                    resultado.setHeaderText("Transferencia cancelada!");
                }
                    resultado.showAndWait();
            }else{
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setContentText("Conta não encontrada!");
                erro.showAndWait();
                }
        }catch (SQLException ex) {
            System.out.println("Erro de Tela: " + ex);
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setContentText("Um Erro occoreu. Por favor, tente mais tarde!");
            erro.showAndWait();
        }    
    }
    
    public void finalizarTransferencia(String remetente, String valor, int destinatario) throws SQLException{
        String sql = "select * from verificarlogin where numeroConta = '"+destinatario+"';"; 
        try{
            Statement state = con.createStatement();
            ResultSet rsd = state.executeQuery(sql); 
            if (rsd.next()){ 
                String efetuarDeposito = "";
                switch(rsd.getInt(8)){
                    case 1:
                       ContaPoupanca cp = new ContaPoupanca(rsd.getInt(1),rsd.getInt(2),rsd.getString(3),rsd.getString(4),rsd.getFloat(5),rsd.getInt(8));
                       cp.efetuarDeposito(Float.parseFloat(valor));
                       efetuarDeposito = "call sp_realizarOperacaoCP('"+cp.getNumeroConta()+"','"+valor+"','"+cp.getSaldo()+"','4','"+remetente+"');";
                       break;
                    case 2:
                        ContaCorrente cc = new ContaCorrente(rsd.getInt(1),rsd.getInt(2),rsd.getString(3),rsd.getString(4),rsd.getFloat(5),rsd.getFloat(6),rsd.getFloat(7),rsd.getInt(8));
                        cc.efetuarDeposito(Float.parseFloat(valor));
                        efetuarDeposito = "call sp_realizarOperacaoCC('"+cc.getNumeroConta()+"','"+valor+"','"+cc.getSaldo()+"','"+cc.getCreditoEspecialUtilizado()+"','4','"+remetente+"');";
                        break;
                }
                state.executeQuery(efetuarDeposito);
            }
        }catch (SQLException ex) {
            System.out.println("Erro de Tela: " + ex);
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setContentText("Um Erro occoreu. Por favor, tente mais tarde!");
            erro.showAndWait();
        }    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conexoes c = new Conexoes();
        con = c.conectar();
    }    
}
