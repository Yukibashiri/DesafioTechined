package desafiotechined;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexoes {
    
Connection con;    

    public Connection conectar(){
        String url = "jdbc:mysql://localhost:3306/desafiotechned"; //Caminho para Database;
        String user = "root"; // Usuario do Banco de Dados;
        String senha = "root"; // Senha do banco de dados;
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, senha);
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    
    
public void desconectar(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
}