package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AulaDAO {

    public static boolean createTable() {

        boolean status = false;

        try {

            Statement st = DAO.connection.createStatement();
            String sql = "CREATE TABLE aulas(numAula serial primary key, id_aula VARCHAR(150), nomeAula VARCHAR(50), id_modulo INT, CONSTRAINT moduloAula FOREIGN KEY (id_modulo) REFERENCES modulos(id) , checkAula BOOLEAN);";

            st.executeUpdate(sql);

            st.close();

            status = true;
            System.out.println("Table AULA criada!");
        } catch (Exception e) {
            System.err.println("Table AULA NAO criada! = " + e.getMessage());
        }
        
        return status;
    }

    public static boolean add(String aulaLink, String aulaName, int moduloAula, boolean checkAula) {

        String sql = "INSERT INTO aulas (id_aula, nomeAula, id_modulo, checkAula) VALUES (?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, aulaLink);
            preparedStatement.setString(2, aulaName);
            preparedStatement.setInt(3, moduloAula);
            preparedStatement.setBoolean(4, checkAula);

            //ResultSet resultSet = preparedStatement.executeQuery();
            @SuppressWarnings("unused")
            boolean execute = preparedStatement.execute();

            System.out.println("AULA INSERIDA!");

            return true;
            
        } catch (Exception e) {

            System.out.println("AULA NÃO INSERIDA!" + e);

            return false;
        }

    }

    public static boolean deleteAula(int id) {

        String sql = "DELETE FROM aulas WHERE numaula = ?;";

        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            //ResultSet resultSet = preparedStatement.executeQuery();
            @SuppressWarnings("unused")
            boolean execute = preparedStatement.execute();

            System.out.println("AULA DELETADA!");

            return true;
            
        } catch (Exception e) {

            System.out.println("AULA NÃO DELETADA!" + e);

            return false;
        }

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

    public static String showAulas(int modulo) {
        
        String sql = "SELECT * FROM public.aulas WHERE id_modulo=? ORDER BY numaula;";
        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, modulo);

            ResultSet resultSet = preparedStatement.executeQuery();

            String id, name, numeroAula;

            ArrayList<String> arrayList = new ArrayList<String>();

            while (resultSet.next()) {

                /*
                 * System.out.print("'Id:" + resultSet.getString("id_user") + "' ");
                 * System.out.print("'User:" + resultSet.getString("name") + "' ");
                 * System.out.print("'Email:" + resultSet.getString("email") + "' ");
                 * System.out.println("'Passoword:" + resultSet.getString("password") + "' ");
                 */

                id = resultSet.getString("id_aula");
                name = resultSet.getString("nomeAula");
                numeroAula = resultSet.getString("numAula");

                arrayList.add("{\"numAula\": \"" + numeroAula + "\", \"id\": \"" + id + "\", \"name\": \"" + name + "\"}");
            }

            //System.out.println(arrayList);

            return arrayList.toString();

        } catch (Exception e) {

        }
        return "Erro";

    }

    public static boolean update(int id, String numAula, String name, String linkYoutube) {
        
        String sql = "";
        if (!linkYoutube.equals("")) {
            sql = "UPDATE aulas SET id_aula=? WHERE numAula=?;";
            try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {
            
                preparedStatement.setString(1, linkYoutube);
                preparedStatement.setInt(2, id);
                
                //ResultSet resultSet = preparedStatement.executeQuery();
                @SuppressWarnings("unused")
                boolean execute = preparedStatement.execute();
    
                
                System.out.println("MODULO Atualizado!");
                
            } catch (Exception e) {
    
                System.out.println("MODULO NÃO Atualizado!" + e);
    
            } 
        }

        if (!name.equals("")) {
            sql = "UPDATE aulas SET nomeAula=? WHERE numAula=?;";
            try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {
            
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, id);
                
                //ResultSet resultSet = preparedStatement.executeQuery();
                @SuppressWarnings("unused")
                boolean execute = preparedStatement.execute();
    
                
                System.out.println("MODULO Atualizado!");
                
            } catch (Exception e) {
    
                System.out.println("MODULO NÃO Atualizado!" + e);
    
            } 
        }

        if (!numAula.equals("")) {
            sql = "UPDATE aulas SET numAula=? WHERE numAula=?;";
            try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {
            
                preparedStatement.setInt(1, Integer.parseInt(numAula));
                preparedStatement.setInt(2, id);
                
                //ResultSet resultSet = preparedStatement.executeQuery();
                @SuppressWarnings("unused")
                boolean execute = preparedStatement.execute();
    
                
                System.out.println("MODULO Atualizado!");
                
            } catch (Exception e) {
    
                System.out.println("MODULO NÃO Atualizado!" + e);
    
            } 
        }

        if (sql != "") {
            return true;
        }

        return false;

    }

}
