package com.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {

    public static String driverName = "org.postgresql.Driver";
    public static String serverName = "enzopucmg.postgres.database.azure.com";
    public static int porta = 5432;
    public static String mydatabase = "postgres"; // Nome do Banco de dados

    //public static String url = "jdbc:postgresql://enzopucmg.postgres.database.azure.com:5432/postgres?user=adm&password=@PucMinas&sslmode=require";

    // jdbc:postgresql://localhost:5432/postgres
    private static String user = "adm";
    private static String password = "@PucMinas";
    
    public static String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase + "?user=" + user + "&password=" + password + "&sslmode=require";

    protected static Connection connection;

    public DAO() {

        connection = null;

    }

    public static Connection getConnection() {

        try {

            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connection Successful!");

        } catch (Exception e) {

            System.err.println("Database Connection NOT Successful! = " + e.getMessage());

        }

        return connection;
    }

    public static Connection close() {

        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;

    }

}
