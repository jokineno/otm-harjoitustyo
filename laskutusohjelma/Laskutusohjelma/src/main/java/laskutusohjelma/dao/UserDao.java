
package laskutusohjelma.dao;

import java.sql.SQLException;
import java.util.List;
import laskutusohjelma.domain.User;

/**
 * interface UserDao defines methods used in FileUserDao
 * @author ollijokinen
 * @param <T> first parameter
 * @param <K> second parameter
 */
public interface UserDao<T, K> {
    
    void create(T user) throws SQLException;
    boolean findByUsername(K username) throws SQLException;
    K returnUsernameByName(K name) throws SQLException;
    K returnNameByUsername(K username) throws SQLException;
    K returnYNumber(K name) throws SQLException;
    K returnBankAccount(K username) throws SQLException;
    void save(T user) throws SQLException;
}
