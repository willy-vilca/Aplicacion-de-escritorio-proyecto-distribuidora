package proyfinalbd2.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexion {

    public static Connection conectar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    Connection conectar=null;
    
    String usuario ="postgres.kfrwmklssjkkubsknepv";
    String contrasenia="distribuidora";
    String bd="postgres";
    String ip="aws-1-sa-east-1.pooler.supabase.com";
    String puerto="5432";
    
//    String usuario ="postgres";
//    String contrasenia="admin";
//    String bd="dbDistribuidora";
//    String ip="localhost";
//    String puerto="54321";  
    
    String url="jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        try{
            Class.forName("org.postgresql.Driver");
            
            conectar= DriverManager.getConnection(url, usuario, contrasenia);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
        
        return conectar;
    }
}