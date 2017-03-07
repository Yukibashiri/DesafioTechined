/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiotechined;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import static desafiotechined.ControllerTelaLogin.usuarioAtual;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Yukibashiri
 */
public class ControllerExtrato implements Initializable {
    Connection con;
    @FXML Label lblConta;
    @FXML TableView<Transacoes> table;
    @FXML TableColumn<Transacoes, String> operacaoColumn;
    @FXML TableColumn<Transacoes, Date> dataOperacaoColumn;
    @FXML TableColumn<Transacoes, Float> valorColumn;
    @FXML TableColumn<Transacoes, String> InformacaoColumn;
    private ObservableList<Transacoes> extratos = FXCollections.observableArrayList();
    
    
    //Busca no banco de dados todas as transações que o usuario fez no ultimo mês.
    @FXML public void carregarTransacoes(){
        table.getItems().clear();
        try{
            String query = "select DataOperacao,operacao,valor, informacaoAdicional from v_extratoMes where numeroConta = '"+usuarioAtual.getConta()+"';";   
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(query); 
            lblConta.setText(String.valueOf(usuarioAtual.getConta()));
            while (rs.next()){
                extratos.add(new Transacoes(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerExtrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataOperacaoColumn.setCellValueFactory(new PropertyValueFactory<>("dataOperacao"));
        operacaoColumn.setCellValueFactory(new PropertyValueFactory<>("operacao"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        InformacaoColumn.setCellValueFactory(new PropertyValueFactory<>("InformacaoAdicional"));
        table.setItems(extratos);
    }
    
    //Simples metodo para voltar para o menu principal.
    @FXML public void voltar(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLMenuOperacoes.fxml", "Menu de Operações");
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conexoes c = new Conexoes();
        con = c.conectar();
    }    
    
}
