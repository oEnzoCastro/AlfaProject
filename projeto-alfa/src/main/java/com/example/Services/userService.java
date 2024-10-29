package com.example.Services;

import com.example.DAO.userDAO;

import spark.Request;
import spark.Response;

public class userService {

    public static boolean createTable() {

        userDAO.createTable();

        return true;
    }

    public static boolean add(Request request, Response response) {

        boolean status = false;

        try {

            String user = request.queryParams("user");
            String email = request.queryParams("email");
            String password = request.queryParams("password");
            
            userDAO.add(user, email, password);

            status = true;

        } catch (Exception e) {
            System.err.println("User não inserido! = " + e.getMessage());
        }

        return status;

    }

    public static boolean delete(Request request, Response response) {
        boolean status = false;

        try {

            int id = Integer.parseInt(request.queryParams("id"));
            
            userDAO.delete(id);

            status = true;

        } catch (Exception e) {
            System.err.println("User não deletado! = " + e.getMessage());
        }

        return status;
    }

    public static boolean update(Request request, Response response) {

        boolean status = false;

        try {

            int id = Integer.parseInt(request.queryParams("id"));
            String user = request.queryParams("user");
            String email = request.queryParams("email");
            String password = request.queryParams("password");
            
            userDAO.update(id, user, email, password);

            status = true;

        } catch (Exception e) {
            System.err.println("User não atualizado! = " + e.getMessage());
        }

        return status;

    }

    public static String getAll(Request request, Response response) {


        try {
            
            return userDAO.getAll();


        } catch (Exception e) {
            System.err.println("User não atualizado! = " + e.getMessage());
        }

        return "";

    }


}
