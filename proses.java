package responsi;

import java.sql.*;

/**
 *
 * @author User
 */
public class proses {
    private String query;
    private ResultSet rs;
    private Statement stmt;
    private ResultSet rs_c;
    private Statement stmt_c;
    private int jumBaris;
    
    public String [][]getForm(){
        connector kon = new connector();
        Connection connect = kon.connector();
        String data [][]= null;
        try{
            stmt = connect.createStatement();
            query = "SELECT id,judul,genre,penulis,penerbit,lokasi,stok " + " from databuku "
                    + " order by id";
            rs = stmt.executeQuery(query);
            ResultSetMetaData meta = rs.getMetaData();
            int jmlKolom = meta.getColumnCount();
            data = new String [1000][jmlKolom];
            int r = 0;
            while (rs.next()){
                for(int c=0; c<jmlKolom; c++){
                    data[r][c]=rs.getString(c+1);
                }
                r++;
            }
            int jmlBaris = r;
            String [][]tmparray = data;
            data = new String[jmlBaris][jmlKolom];
            for(r=0; r<jmlBaris; r++){
                System.arraycopy(tmparray [r], 0, data[r], 0, jmlKolom);
            }
            stmt.close();
            connect.close();
        }catch(SQLException ex){
             System.out.println("Error : " + ex.getMessage());
        }
        return data;
    }
    
    public int tambah (String judul, String genre, String penulis, String penerbit, String lokasi, String stok){
        jumBaris = 0;
        connector kon = new connector();
        Connection connect = kon.connector();
        try{
            stmt = connect.createStatement();
            query = "insert into databuku (judul, genre, penulis, penerbit, lokasi, stok)" 
                    + " values ('"+judul+"','"+genre+"','"+penulis+"','"+penerbit+"','"+lokasi+"', '"+stok+"')";
            stmt.executeUpdate(query);
            stmt_c = connect.createStatement();
            rs_c = stmt_c.executeQuery("SELECT COUNT(*) FROM 'databuku'");
            while (rs_c.next()){
                jumBaris = rs_c.getInt(1);
            }
            stmt.close();
            stmt_c.close();
            connect.close();
        }catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
        }
        return jumBaris;
    }
    
    public void Hapus (String id){
        connector kon = new connector();
        Connection connect = kon.connector();
        try{
            stmt = connect.createStatement();
            query = "DELETE FROM siswa WHERE id='"+id+"'";
            stmt.executeUpdate(query);
            
            stmt.close();
            connect.close();
        }catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
        } 
    }
    
    public void Update(int id, String judul, String genre, String penulis, String penerbit, String lokasi, String stok){
        connector kon = new connector();
        Connection connect = kon.connector();
        try{
            stmt = connect.createStatement();
            query = "update databuku set judul ='"+judul+"', genre ='"+genre+"', penulis ='"+penulis+"', penerbit='"+penerbit+"', lokasi ='"+lokasi+"', stok='"+stok+"'" 
                    +" where id='"+id+"'";
            stmt.executeUpdate(query);
            
            stmt.close();
            connect.close();
        }catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
        }
    }
    
    public String getData(int id){
        connector kon = new connector();
        Connection connect = kon.connector();
        String data = null;
        try{
            stmt = connect.createStatement();
            query = "SELECT judul, genre, penulis, penerbit, lokasi, stok FROM databuku " + "where id= '"+id+"'";
            rs = stmt.executeQuery(query);
            while (rs.next()){
                data  = rs.getString(1);
            }
            rs.close();
            stmt.close();
            connect.close();
        }catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
        }
        return data;
    }
}