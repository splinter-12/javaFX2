package ma.enset.tpBaseDonnes.DAO;

import java.sql.SQLException;
import java.util.List;

public interface dao <T>{
    List<T> finAll() throws SQLException;
    T findOne(int id) throws SQLException;
    T save(T o) throws SQLException;
    boolean delete(T o) throws SQLException;
    T update(T o) throws SQLException;
}
