package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ModuloDAO {

    public static void createTable() {

        try {

            Statement st = DAO.connection.createStatement();
            String sql = "CREATE TABLE modulos(id serial primary key, name VARCHAR(50));";

            st.executeUpdate(sql);

            st.close();

            System.out.println("Table MODULOS criada!");

        } catch (Exception e) {

            System.err.println("Table MODULOS NAO criada! = " + e.getMessage());

        }

    }

    public static void add(String nome) {

        String sql = "INSERT INTO modulos (name)" + " VALUES (?)";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nome);

            //ResultSet resultSet = preparedStatement.executeQuery();
            @SuppressWarnings("unused")
            boolean execute = preparedStatement.execute();

            System.out.println("MODULO INSERIDO!");
            
        } catch (Exception e) {

            System.out.println("MODULO NÃO INSERIDO!" + e);

        }

    }

    public static boolean delete(int id) {

        String sql = "DELETE FROM modulos WHERE id=?;";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);

            //ResultSet resultSet = preparedStatement.executeQuery();
            @SuppressWarnings("unused")
            boolean execute = preparedStatement.execute();

            System.out.println("Modulo Deletado!");

            return true;


        } catch (Exception e) {
            System.err.println("Modulo não deletado! = " + e.getMessage());
            return false;
        }

    }

    public static void update(int id, String user) {

        String sql = "";
        if (!user.equals("")) {
            sql = "UPDATE modulos SET name= ? WHERE id=?;";
        }
        
        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, user);
            preparedStatement.setInt(2, id);
            
            //ResultSet resultSet = preparedStatement.executeQuery();
            @SuppressWarnings("unused")
            boolean execute = preparedStatement.execute();

            
            System.out.println("MODULO Atualizado!");
            
        } catch (Exception e) {

            System.out.println("MODULO NÃO Atualizado!" + e);

        }
    }

    public static String getAll() {

        String sql = "SELECT * FROM public.modulos ORDER BY id;";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            String id, name;

            ArrayList<String> arrayList = new ArrayList<String>();

            while (resultSet.next()) {
                /*
                 * System.out.print("'Id:" + resultSet.getString("id_user") + "' ");
                 * System.out.print("'User:" + resultSet.getString("name") + "' ");
                 * System.out.print("'Email:" + resultSet.getString("email") + "' ");
                 * System.out.println("'Passoword:" + resultSet.getString("password") + "' ");
                 */
                id = resultSet.getString("id");
                name = resultSet.getString("name");

                arrayList.add("{\"id\": \"" + id + "\", \"name\": \"" + name + "\"}");
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

                if (resultSet.getString("password").equals(password)) { // Confere se a senha (BD) é igual a senha
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
