package app;

import java.sql.Connection;
import java.sql.SQLException;

import dao.DAO;

public class app {
    public static void main(String[] args) throws SQLException {
        
        Connection connection = DAO.getConnection();

        connection.close();

    }
}
