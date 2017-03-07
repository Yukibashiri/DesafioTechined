/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiotechined;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yukibashiri
 */
public class ControllerCadastro implements Initializable {
    Connection con;
    @FXML TextField tfConta;
    @FXML TextField tfAgencia;
    @FXML TextField tfDeposito;
    @FXML TextField tfTitular;
    @FXML ChoiceBox cbTipoConta;
    @FXML Button btnGerar;
    @FXML TextField tfSenha;
    @FXML DatePicker dpData;
    Stage stage;
    private  final ObservableList<String> listatiposConta = FXCollections.observableArrayList("Conta Poupança","Conta Corrente");
    private boolean verify = false;
    
    // Método que carrega as informações do campo com a data atual e os tipos de conta.
    @FXML public void loadDados(){
        if (verify == false){
            //dpData.setValue(LocalDate.now());
            cbTipoConta.setItems(listatiposConta);
            cbTipoConta.setValue("Conta Poupança");
            verify = true;
        }
        
    }
     
    @FXML public boolean verificarCampos(){
        return !(tfConta.getText().equals("") || tfAgencia.getText().equals("") || tfDeposito.getText().equals("") || tfTitular.getText().equals("") || tfSenha.getText().equals(""));
    }
    
    @FXML public void gerarNumeroDeConta(){
        Random gerador = new Random();
        int numeroConta = 1000+ gerador.nextInt(9000); // Quero que o numero aleatorio esteja entre 1000 e 9999, logo inicio o com 1000 (com o +1000) e limito as possibilidades até 9999 com (nextInt(9000) pois são 8999 possibilidades + 0, logo 9000, criando meu alcance de 1000 a 9999.
        String sql = "select cln_numero from tbl_contas where cln_numero = '"+numeroConta+"';";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql); 
            if (!(rs.next())){
                tfConta.setText(Integer.toString(numeroConta));
                btnGerar.setVisible(false);
                btnGerar.setDisable(true);
            }else{
                gerarNumeroDeConta();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    @FXML public void limpar(){
        tfConta.setText("");
        tfAgencia.setText("");
        tfDeposito.setText("");
        tfTitular.setText("");
    }
    
    @FXML public void voltar(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLTelaLogin.fxml", "Tela de Login");
    } 
    
    // Sistema simples de Cadastro só para possibilitar a criação de novas contas para efetuar testes.
    @FXML public void cadastrar(){
        if (verificarCampos() == true){
            String novoCliente = "call sp_cadastro ('"+cbTipoConta.getValue()+"','"+tfTitular.getText()+"','"+tfConta.getText()+"','"+tfAgencia.getText()+"','"+tfDeposito.getText()+"','"+tfSenha.getText()+"');";
            try { 
                Statement stat = con.createStatement();
                stat.executeQuery(novoCliente);
                 Alert info = new Alert(Alert.AlertType.INFORMATION);
                 info.setHeaderText("Cliente cadastrado com sucesso!");
                 info.showAndWait();
                 stage = (Stage) tfConta.getScene().getWindow();
                 stage.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setContentText("Preencha todos os campos!");
            erro.showAndWait();
        }
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conexoes c = new Conexoes();
        con = c.conectar();
    }    
    
}
