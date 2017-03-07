/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiotechined;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import static desafiotechined.ControllerTelaLogin.usuarioAtual;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Yukibashiri
 */
public class ControllerSaldo implements Initializable {
    Connection con;
    @FXML Label lblSaldoAtual;
    @FXML Label lblCreditoEspecial;
    @FXML Label lblValorEspecial;
    @FXML Label lblValorUtilizado;
    @FXML Label lblRestante;        
    
    
    @FXML public void voltarMenu(ActionEvent evento) throws IOException{
        TrocarTelas tela = new TrocarTelas();
        tela.trocar(evento, "FXMLMenuOperacoes.fxml", "Menu de Operações");
    }
    
    @FXML public void verificarSaldo(){
        String sql = "select * from verificarlogin where numeroConta = '"+usuarioAtual.getConta() +"'";
        try {
            Statement stat = con.createStatement(); 
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()){
                switch(usuarioAtual.getTipo()){
                    case 1:
                        ContaPoupanca cp = new ContaPoupanca(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getInt(8));
                        lblSaldoAtual.setText(Float.toString(cp.getSaldo()));
                        break;
                    case 2:
                        ContaCorrente cc = new ContaCorrente(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8));
                        lblSaldoAtual.setText(Float.toString(cc.getSaldo()));
                        lblCreditoEspecial.setVisible(true);
                        lblValorEspecial.setVisible(true);
                        lblValorEspecial.setText(Float.toString(cc.verCreditoEspecial()));
                        if (cc.getCreditoEspecialUtilizado() > 0){
                            lblValorUtilizado.setVisible(true);
                            lblRestante.setVisible(true);
                            lblValorUtilizado.setText(Float.toString(cc.getCreditoEspecialUtilizado()));
                        }
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSaldo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conexoes c = new Conexoes();
        con = c.conectar(); // chama o metodo que irá tentar conectar ao banco de dados.
        
    }    
    
}
