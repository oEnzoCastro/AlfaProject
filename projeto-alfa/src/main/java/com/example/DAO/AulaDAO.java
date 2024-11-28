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
            String sql = "CREATE TABLE aulas(numAula serial primary key, id_aula VARCHAR(50), nomeAula VARCHAR(50), id_modulo INT, CONSTRAINT moduloAula FOREIGN KEY (id_modulo) REFERENCES modulos(id) , checkAula BOOLEAN);";

            st.executeUpdate(sql);

            st.close();

            status = true;
            System.out.println("Table AULA criada!");
        } catch (Exception e) {
            System.err.println("Table AULA NAO criada! = " + e.getMessage());
        }
        
        return status;
    }

    public static boolean insertAula(String aulaLink, String aulaName, int moduloAula, boolean checkAula) {
        
        boolean status = false;
        
        try {
            
            Statement st = DAO.connection.createStatement();
            String sql = "INSERT INTO aulas (id_aula, nomeAula, id_modulo, checkAula) VALUES ('" + aulaLink + "', '" + aulaName + "', '" + moduloAula + "', '" + checkAula + "');";
            
            st.executeUpdate(sql);
            
            st.close();
            
            System.out.println("Aula inserida!");
            status = true;
        } catch (Exception e) {
            System.err.println("Aula não inserida! = " + e.getMessage());
        }

        return status;
    }

    public static boolean deleteAula(int id) {

        boolean status = false;
        
        try {
            
            Statement st = DAO.connection.createStatement();
            String sql = "DELETE FROM aulas WHERE numaula = " + id + ";";
            
            st.executeUpdate(sql);
            
            st.close();
            
            System.out.println("Aula Deletada!");
            status = true;
        } catch (Exception e) {
            System.err.println("Aula não deletada! = " + e.getMessage());
        }

        return status;

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

        boolean status = false;
        
        String sql = "SELECT * FROM public.aulas WHERE id_modulo=? ORDER BY numaula;";
        try (PreparedStatement preparedStatement = DAO.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, modulo);

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

                id = resultSet.getString("id_aula");
                name = resultSet.getString("nomeAula");

                arrayList.add("{\"id\": \"" + id + "\", \"name\": \"" + name + "\"}");
            }

            //System.out.println(arrayList);

            return arrayList.toString();

        } catch (Exception e) {

        }
        return "Erro";

    }

    public static boolean findUser(String user, String password) {

        boolean status = false;
        ResultSet resultSet = null;
        
        try {
            
            Statement st = DAO.connection.createStatement();
            String sql = "SELECT * FROM public.users;";
            
            ArrayList<String> arrayList = new ArrayList<String>();

            resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                arrayList.add(resultSet.getString("name") + resultSet.getString("password"));
            }

            System.out.println(arrayList + " " + user + " " + password);
            
            if (arrayList.contains(" " + user + password)) {
                System.out.println("Usuário Encontrado!");

                return true;

            } else {
                System.out.println("Usuário Não Encontrado!");

                return false;

            }
        } catch (Exception e) {
            System.err.println("ERRO DETECTADO NA LEITURA DE DADOS! = " + e.getMessage());
        }

        return status;

    }

}
