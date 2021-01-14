package responsi;

import java.sql.*;
/**
 *
 * @author User
 */
public class connector {
    String url ="jdbc:mysql://localhost/perpustakaan";
    String user="root";
    String pass="";
    public Connection koneksi = null;
    public Connection connector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");   
            koneksi = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi Berhasil");
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Koneksi gagal: "+ ex.getMessage());
        }
        return koneksi;
    }
}
