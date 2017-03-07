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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yukibashiri
 */
public class ControllerTelaLogin implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection con; // Utilizado para conectar ao bando de dados
    @FXML TextField tfConta; // Recebe a conta do usuario
    @FXML PasswordField pfSenha; // Recebe a senha do usuario
    static Usuario usuarioAtual = new Usuario();
    @FXML public void sair() {
        System.exit(0);
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conexoes c = new Conexoes();
        con = c.conectar(); // chama o metodo que irá tentar conectar ao banco de dados.
    }    
    
    @FXML public void cadastro() throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar("FXMLCadastro.fxml", "Cadastro de novos Clientes");
    }
    public void acessar(ActionEvent evento) throws IOException, SQLException {  
        String sql = "select * from verificarlogin where numeroConta = '"+tfConta.getText()+"' and senha = '"+pfSenha.getText()+"';";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql); 
            if (rs.next()){ 
                usuarioAtual.setConta(rs.getInt("numeroConta"));
                usuarioAtual.setTipo(rs.getInt("tipoConta"));
                TrocarTelas tela = new TrocarTelas();
                tela.trocar(evento, "FXMLMenuOperacoes.fxml", "Menu de Operações");
            }else{
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setContentText("Login ou senha invalidos!");
                erro.showAndWait();
            }
        }catch (SQLException ex) {
            System.out.println("Erro de Tela: " + ex);
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setContentText("Login ou senha invalidos!");
            erro.showAndWait();
        }
    }
}
