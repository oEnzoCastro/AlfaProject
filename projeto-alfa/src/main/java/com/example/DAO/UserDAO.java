package com.example.DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static String MD5(String senha) {

        MessageDigest messageDigest;
        try {

            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(senha.getBytes(), 0, senha.length());

            return new BigInteger(1,messageDigest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erro na criptografia: " + e);
        }

        return senha;
    }

    public static void add(String user, String email, String password) {

        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?);";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)){

            preparedStatement.setString(1, user);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, MD5(password));

            //ResultSet resultSet = preparedStatement.executeQuery();
            @SuppressWarnings("unused")
            boolean execute = preparedStatement.execute();

            System.out.println("Usuario inserido!");

        } catch (Exception e) {

            System.err.println("Usuario não inserido! = " + e.getMessage());

        }

    }

    public static void delete(int id) {

        String sql = "DELETE FROM users WHERE id_user=?;";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            //ResultSet resultSet = preparedStatement.executeQuery();
            @SuppressWarnings("unused")
            boolean execute = preparedStatement.execute();

            System.out.println("Usuario Deletado!");

        } catch (Exception e) {
            System.err.println("Usuario não deletado! = " + e.getMessage());
        }

    }

    public static boolean update(int id, String user, String email, String password) {
        String sql = "";
        if (!user.equals("")) {
            sql = "UPDATE users SET name=? WHERE id_user=?;";

            try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {
            
                preparedStatement.setString(1, user);
                preparedStatement.setInt(2, id);
                
                //ResultSet resultSet = preparedStatement.executeQuery();
                @SuppressWarnings("unused")
                boolean execute = preparedStatement.execute();
    
                System.out.println("Usuario Nome Atualizado!");
            } catch (Exception e) {
                System.err.println("Usuario Nome não Atualizado! = " + e.getMessage());
            }

        }
        if (!email.equals("")) {
            sql = "UPDATE users SET email=? WHERE id_user=?;";

            try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {
            
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, id);
                
                //ResultSet resultSet = preparedStatement.executeQuery();
                @SuppressWarnings("unused")
                boolean execute = preparedStatement.execute();
    
                System.out.println("Usuario Email Atualizado!");
            } catch (Exception e) {
                System.err.println("Usuario Email não Atualizado! = " + e.getMessage());
            }

        }
        if (!password.equals("")) {
            sql = "UPDATE users SET password=? WHERE id_user=?;";

            try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {
            
                preparedStatement.setString(1, MD5(password));
                preparedStatement.setInt(2, id);
                
                //ResultSet resultSet = preparedStatement.executeQuery();
                @SuppressWarnings("unused")
                boolean execute = preparedStatement.execute();
    
                System.out.println("Usuario Senha Atualizado!");
            } catch (Exception e) {
                System.err.println("Usuario Senha não Atualizado! = " + e.getMessage());
            }

        }

        if (sql != "") {
            return true;
        }

        return false;

    }

    public static String getAll() {

        String sql = "SELECT * FROM public.users ORDER BY id_user;";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            String id, user, email, password;

            ArrayList<String> arrayList = new ArrayList<String>();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                id = resultSet.getString("id_user");
                user = resultSet.getString("name");
                email = resultSet.getString("email");
                password = resultSet.getString("password");

                arrayList.add("{\"id\": \"" + id + "\", \"user\": \"" + user + "\", \"email\": \"" + email + "\", \"password\": \"" + password + "\"}");
            }

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

                if (resultSet.getString("password").equals(MD5(password))) { // Confere se a senha (BD) é igual a senha
                                                                        // (Input)
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

    public static String get(int userId) {

        String sql = "SELECT * FROM users WHERE id_user=?";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return "{\"user\": \"" + resultSet.getString("name") + "\", \"email\": \"" + resultSet.getString("email") + "\", \"password\": \"" + resultSet.getString("password") + "\"}";
            }

        } catch (Exception e) {

        }

        return "";

    }

}
