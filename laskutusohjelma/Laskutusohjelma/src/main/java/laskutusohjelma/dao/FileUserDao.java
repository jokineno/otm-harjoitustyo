
package laskutusohjelma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import laskutusohjelma.domain.SQLiteDatabase;
import laskutusohjelma.domain.User;

public class FileUserDao implements UserDao<User, String> {
    private SQLiteDatabase database;
    
    public FileUserDao() {
        this.database = database;
    }
    
    @Override
    public void create(User user) throws SQLException {
        try{
            Connection conn = database.getConn();
       

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User"
                + " (name, username, yNumber, accountNumber)"
                + " VALUES (?, ?, ?, ?)");

        stmt.setString(1, user.getName());
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getYtunnus());
        stmt.setString(4, user.getTilinumero());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    /*
    when logging in the system checks if your username exists. if not, it wont't log in. if so it logs user in
    */

    @Override
    public boolean findByUsername(String username) throws SQLException {
        try {
            Connection conn = database.getConn();
        
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setObject(1, username);
        
        ResultSet rs = stmt.executeQuery();
        
        boolean hasOne = rs.next();
        if (!hasOne) {
            return false;
        }
        
        stmt.close();
        rs.close();
        conn.close();
        return true;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    @Override
    public String returnUsernameByName(String name) throws SQLException {
        try {
        
        Connection conn = database.getConn();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE name = ?");
        stmt.setObject(1, name);
        ResultSet rs = stmt.executeQuery();
        String name1 = rs.getString("name");
        
        stmt.close();
        rs.close();
        conn.close();
        
        
        return name1;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        
        return null;
    }
    
    @Override
    public String returnNameByUsername(String name) throws SQLException {
        try {
            Connection conn = database.getConn();
        
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM User WHERE username = ?");
            stmt.setObject(1, name);
            ResultSet rs = stmt.executeQuery();
            String name1 = rs.getString("name");
        
            stmt.close();
            rs.close();
            conn.close();
       
            return name1;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @Override
    public String returnYNumber(String username) throws SQLException {
        try {
        
        Connection conn = database.getConn();
        PreparedStatement stmt = conn.prepareStatement("SELECT yNumber FROM User WHERE username = ?");
        stmt.setObject(1, username);
        ResultSet rs = stmt.executeQuery();
        String yNumber1 = rs.getString("yNumber");
        
        stmt.close();
        rs.close();
        conn.close();
        
        return yNumber1;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @Override
    public String returnBankAccount(String username) throws SQLException {
        try {
        
        Connection conn = database.getConn();
        PreparedStatement stmt = conn.prepareStatement("SELECT accountNumber FROM User WHERE username = ?");
        stmt.setObject(1, username);
        ResultSet rs = stmt.executeQuery();
        String accountNumber1 = rs.getString("accountNumber");
        
        stmt.close();
        rs.close();
        conn.close();
        
        return accountNumber1;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    
    public String returnUsernameByname(String name) throws SQLException {
        try {
            Connection conn = database.getConn();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT username FROM User WHERE name = ?");
        stmt.setObject(1, name);
        ResultSet rs = stmt.executeQuery();
        String username = rs.getString("username");
        
        stmt.close();
        rs.close();
        conn.close();
        
        return username;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    @Override
    public void save(User user) throws SQLException {
        try {
           
        
        Connection conn = this.database.getConn();
        String updateByUsername = user.getUsername();
        String sql = "UPDATE User SET name = ?, yNumber = ?, accountNumber =? WHERE username = '" + updateByUsername + "'";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, user.getName());
        stmt.setObject(2, user.getYtunnus());
        stmt.setObject(3, user.getTilinumero());
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public User returnUserByName(String name) throws SQLException {
        String name1 = name;
        String usName = returnUsernameByName(name);
        String yNumber = returnYNumber(name);
        String bank = returnBankAccount(name);
        User user = new User(name, usName, yNumber, bank);
        
        return user;
    }
}