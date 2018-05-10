
package laskutusohjelma.dao;
import java.util.*;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import laskutusohjelma.domain.Asiakas;
import laskutusohjelma.domain.SQLiteDatabase;

public class FileAsiakasDao implements AsiakasDao<Asiakas, String> {
    
    final SQLiteDatabase database; 
    
    public FileAsiakasDao() {
        this.database = new SQLiteDatabase();
    }
    
     /**
     * etsitään asiakas tietokannasta
     * @param event
     * @throws java.sql.SQLException
     * @throws IOException 
     */
    
    public Integer tableSize() throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Customer");
        ResultSet rs = stmt.executeQuery();
        Integer tableSize = null;
        
        while(rs.next()) {
            tableSize = rs.getInt(1);
        }
        
        
        return tableSize;
    }
    
    @Override
    public void createCustomer(Asiakas customer) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Customer" + " (id, name, yNumber)" +  "VALUES (?,?,?)");
        
        
        stmt.setInt(1, tableSize()+1);
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getyTunnus());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
        
    }
    
    @Override
    public String findYNumber(String name) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT yNumber FROM Customer WHERE name = ?");
        stmt.setObject(1, name);
        ResultSet rs = stmt.executeQuery();
        String yNumber = rs.getString("yNumber");
       
     
        stmt.close();
        rs.close();
        conn.close();
        
        return yNumber;
        
    }
  /**
     * etsitään kaikki käyttäjät tietokannasta
     * @param event
     * @return 
     * @throws java.sql.SQLException
     * @throws IOException 
     */
    @Override
    public ObservableList<Asiakas> findAll() throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer");
        
        ResultSet resultSet = stmt.executeQuery();
        ObservableList<Asiakas> asiakkaat = FXCollections.observableArrayList();
        
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String ytunnus = resultSet.getString("yNumber");
            Asiakas a = new Asiakas(id, name, ytunnus);
            asiakkaat.add(a);
            
        }if (asiakkaat.isEmpty()) {
            return null;
        }
        
        stmt.close();
        resultSet.close();
        conn.close();
        return asiakkaat;
    }
   
}
