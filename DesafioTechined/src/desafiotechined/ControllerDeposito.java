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
 * @author  Yukibashiri
 */
public class ControllerDeposito implements Initializable {
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
    
    @FXML public void efetuarDeposito(){
        //utilizaremos a mesma visão para verificar o login, pois os dados que precisamos podem ser obtidos através dela.
        String sql = "select * from verificarlogin where numeroConta = '"+tfConta.getText()+"' and agencia = '"+tfAgencia.getText()+"';"; 
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql); 
            if (rs.next()){ 
                Alert validar = new Alert(Alert.AlertType.CONFIRMATION);
                validar.setTitle("Confirmar Depósito");
                validar.setHeaderText("Titular: "+rs.getString("titular")+". Conta: "+rs.getInt("numeroConta")+". AG: "+rs.getInt("agencia"));
                validar.setContentText("Você deseja efetuar o depósito, no valor de R$: "+tfValor.getText()+" ?");
                Optional<ButtonType> resposta = validar.showAndWait();
                Alert resultado = new Alert(Alert.AlertType.INFORMATION);
                if (resposta.get() == ButtonType.OK){
                    String efetuarDeposito = "";
                    switch(rs.getInt(8)){
                        case 1:
                           ContaPoupanca cp = new ContaPoupanca(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getInt(8));
                           cp.efetuarDeposito(Float.parseFloat(tfValor.getText()));
                           efetuarDeposito = "call sp_realizarOperacaoCP('"+cp.getNumeroConta()+"','"+tfValor.getText()+"','"+cp.getSaldo()+"','2','');";
                           break;
                        case 2:
                            ContaCorrente cc = new ContaCorrente(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8));
                            cc.efetuarDeposito(Float.parseFloat(tfValor.getText()));
                            efetuarDeposito = "call sp_realizarOperacaoCC('"+cc.getNumeroConta()+"','"+tfValor.getText()+"','"+cc.getSaldo()+"','"+cc.getCreditoEspecialUtilizado()+"','2','');";
                            break;
                    }
                    stat.executeQuery(efetuarDeposito);
                    resultado.setHeaderText("Deposito efetuado com sucesso!");
                } else {
                    resultado.setHeaderText("Deposito cancelado!");
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conexoes c = new Conexoes();
        con = c.conectar();
    }    
    
    
}
