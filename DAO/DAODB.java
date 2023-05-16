package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAODB<T> {
    // CRUD
    void create(T t, Connection conn) throws SQLException;

    boolean read(T t, Connection conn) throws SQLException;

    void update(T t, Connection conn) throws SQLException;

    void delete(T t, Connection conn) throws SQLException;

    // ALTRES
    boolean exists(T t, Connection conn) throws SQLException;

}
