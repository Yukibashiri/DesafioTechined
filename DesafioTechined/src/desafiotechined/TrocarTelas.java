/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiotechined;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yukibashiri
 */
public class TrocarTelas {
    
    public void trocar(ActionEvent evento,String fxml, String titulo) throws IOException{
        Parent root =  FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.show();
    }
    
        public void trocar(String fxml, String titulo) throws IOException{
        Parent root =  FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.show();
    }
    
}
