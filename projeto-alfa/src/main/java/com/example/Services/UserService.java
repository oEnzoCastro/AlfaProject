package com.example.Services;

import com.example.DAO.UserDAO;

import spark.Request;
import spark.Response;

public class UserService {

    public static Object createTable() {

        UserDAO.createTable();

        return "";
    }

    public static Object add(Request request, Response response) {

        String user = request.queryParams("user");
        String email = request.queryParams("email");
        String password = request.queryParams("password");
            
        UserDAO.add(user, email, password);

        return "";

    }

    public static Object delete(Request request, Response response) {

        int id = Integer.parseInt(request.queryParams("id"));
            
        UserDAO.delete(id);

        return "";
    }

    public static Object update(Request request, Response response) {

        int id = Integer.parseInt(request.queryParams("id"));
        String user = request.queryParams("user");
        String email = request.queryParams("email");
        String password = request.queryParams("password");
            
        UserDAO.update(id, user, email, password);

        return "";

    }

    public static Object getAll(Request request, Response response) {

        

        return "";

    }


}
