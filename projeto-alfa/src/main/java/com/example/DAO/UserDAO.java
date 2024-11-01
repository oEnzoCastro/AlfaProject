package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    public static void createTable() {
        try {

            Statement st = DAO.connection.createStatement();
            String sql = "CREATE TABLE users(id_user serial primary key, name VARCHAR(50), email VARCHAR(100), password VARCHAR(50));";

            st.executeUpdate(sql);

            st.close();

            System.out.println("Table USER criada!");

        } catch (Exception e) {

            System.err.println("Table USER NAO criada! = " + e.getMessage());

        }
    }

    public static void add(String user, String email, String password) {

        try {

            Statement st = DAO.connection.createStatement();
            String sql = "INSERT INTO users (name, email, password) VALUES ('" + user + "', '" + email + "', '"
                    + password + "');";

            st.executeUpdate(sql);

            st.close();

            System.out.println("Usuario inserido!");

        } catch (Exception e) {

            System.err.println("Usuario não inserido! = " + e.getMessage());

        }

    }

    public static void delete(int id) {

        try {

            Statement st = DAO.connection.createStatement();
            String sql = "DELETE FROM users WHERE id_user = " + id + ";";

            st.executeUpdate(sql);

            st.close();

            System.out.println("Usuario Deletado!");

        } catch (Exception e) {
            System.err.println("Usuario não deletado! = " + e.getMessage());
        }

    }

    public static void update(int id, String user, String email, String password) {

        try {

            Statement st = DAO.connection.createStatement();
            String sql;

            if (!user.equals("")) {
                sql = "UPDATE users SET name='" + user + "' WHERE id_user=" + id + ";";
                st.executeUpdate(sql);
            }
            if (!email.equals("")) {
                sql = "UPDATE users SET email='" + email + "' WHERE id_user=" + id + ";";
                st.executeUpdate(sql);
            }
            if (!password.equals("")) {
                sql = "UPDATE users SET password='" + password + "' WHERE id_user=" + id + ";";
                st.executeUpdate(sql);
            }

            st.close();

            System.out.println("Usuario Atualizado!");
        } catch (Exception e) {
            System.err.println("Usuario não Atualizado! = " + e.getMessage());
        }

    }

    public static String getAll() {

        ResultSet resultSet = null;

        try {

            Statement st = DAO.connection.createStatement();
            String sql = "SELECT * FROM public.users ORDER BY id_user;";

            String id, user, email, password;

            ArrayList<String> arrayList = new ArrayList<String>();

            resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                /*
                 * System.out.print("'Id:" + resultSet.getString("id_user") + "' ");
                 * System.out.print("'User:" + resultSet.getString("name") + "' ");
                 * System.out.print("'Email:" + resultSet.getString("email") + "' ");
                 * System.out.println("'Passoword:" + resultSet.getString("password") + "' ");
                 */
                id = resultSet.getString("id_user");
                user = resultSet.getString("name");
                email = resultSet.getString("email");
                password = resultSet.getString("password");

                arrayList.add("{\"id\": \"" + id + "\", \"user\": \"" + user + "\", \"email\": \"" + email
                        + "\", \"password\": \"" + password + "\"}");
            }

            st.close();
            return arrayList.toString();

        } catch (Exception e) {
            System.err.println("ERRO NA LEITURA DE DADOS! = " + e.getMessage());
        }

        return "";

    }

    public static int login(String user, String password) {

        String sql = "SELECT * FROM users WHERE name=?";
        
        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user);
        
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println(user + " " + password);
                System.out.println(resultSet.getString("name") + " " + resultSet.getString("password"));

                if (resultSet.getString("password").equals(password)) { // Confere se a senha (BD) é igual a senha (Input)
                    System.out.println("SENHA CORRETA");
                    return resultSet.getInt("id_user");
                } else {
                    System.out.println("SENHA INCORRETA");
                    return -1;
                }
            }

            return -1;

        } catch (Exception e) {

        }

        return -1;

    }

}
