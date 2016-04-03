/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author zoedmora
 */
public class DBConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            String host = "jdbc:derby://localhost:1527/Language Users";
            String uName = "zoed";
            String uPass = "zoed";
        
        
            Connection con = DriverManager.getConnection(host, uName, uPass);
        
            Statement stmt = con.createStatement( );
            String SQL = "Select * From Users";
            ResultSet rs = stmt.executeQuery( SQL );
            
            while (rs.next()){
                String name = rs.getString("userName");
                int time = rs.getInt("timeEarned");
                String language = rs.getString("languageTL");
            
            System.out.println(name + " " + time + " " + language);
            }
            
            
        }
        catch (SQLException err){
            System.out.println(err.getMessage());
        }
        
        
        
        
        
    }
    
}
