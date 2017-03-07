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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yukibashiri
 */
public class ControllerMenuOperacoes implements Initializable {
    Connection con;
// Variavel vai verificar se nessa Instancia foi verificado se o usuario pode calcular seu rendimento.
     static boolean rendimentoRealizado = false; 
    
    //Reseta os valores da variavel estatica e volta para a tela de login.
    @FXML public void logout(ActionEvent evento) throws IOException{
        usuarioAtual.setConta(0);
        usuarioAtual.setTipo(0);
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLTelaLogin.fxml", "Tela de Login");
    } 
    
    // Chama a tela que apresenta o saldo da conta
    @FXML public void saldo(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLSaldo.fxml", "Saldo Atual");
    }
    // Chama a tela do Extrato Bancário
    @FXML public void extrato(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLExtrato.fxml", "Extrato Bancário");
    }
    // Chama a tela de Depósito Bancário
    @FXML public void deposito(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLDeposito.fxml", " Tela de Depósito");
    }
    // Chama a tela de Saque
    @FXML public void saque(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLSaque.fxml", " Tela de Saque");
    }
    // Chama a tela de Transferencia Bancária
    @FXML public void transferencia(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLTransferencia.fxml", " Tela de Transferência");
    }

    // Não sei como funciona esse sistema de rendimento, mas da forma montada abaixo, se o Usuario não Transferiu ou Sacou nenhum dinheiro no ultimo mês
    // e não obteve nenhum rendimento, Então ele irá estar apto a realizar o rendimento. Esse Metodo irá rodar toda vez que o usuario logar ou voltar para o menu.
    @FXML private void realizarRendimento(){
        if(rendimentoRealizado == false && usuarioAtual.getTipo() == 1){
            String sql = "SELECT * FROM v_extratomesr WHERE numeroConta = '"+usuarioAtual.getConta()+"' AND (operacao = 'Transferência Realizada' OR operacao = 'Saque' OR operacao = 'Rendimento');";
            try { 
                Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery(sql);
                if (!(rs.next())){
                    sql = "select * from verificarlogin where numeroConta = '"+usuarioAtual.getConta()+"';";
                    ResultSet rsa = stat.executeQuery(sql);
                    rsa.next();
                    ContaPoupanca cp = new ContaPoupanca(rsa.getInt(1),rsa.getInt(2),rsa.getString(3),rsa.getString(4),rsa.getFloat(5),rsa.getInt(8));
                    float valorRendimento = cp.getRendimento();
                    cp.aplicarRendimento();
                    String aplicarRendimento = "call sp_realizarOperacaoCP('"+cp.getNumeroConta()+"','"+valorRendimento+"','"+cp.getSaldo()+"','5','');";
                    stat.executeQuery(aplicarRendimento);
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setContentText("Rendimento aplicado!");
                    info.showAndWait();
                    rendimentoRealizado = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMenuOperacoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conexoes c = new Conexoes();
        con = c.conectar();
    }    
}
