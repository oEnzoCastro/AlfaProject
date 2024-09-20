package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    public static String driverName = "org.postgresql.Driver";
    public static String serverName = "localhost";
    public static String mydatabase = "postgres";
    public static int porta = 5432;

    public static String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;

    // jdbc:postgresql://localhost:5432/postgres
    private static String user = "postgres";
    private static String password = "SenhaPostgres";

    private DAO() {

    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connection Successful!");

        } catch (Exception e) {

            System.out.println("Database Connection NOT Successful! = " + e);

        }

        return connection;
    }

}
