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

        if (request.url() == "http://localhost:5500/projeto-alfa/src/resources/adm.html") {
            response.redirect("http://localhost:5500/projeto-alfa/src/resources/adm.html");
        } else {
            response.redirect("http://localhost:5500/projeto-alfa/src/resources/login.html");
        }

        return "";

    }

    public static Object delete(Request request, Response response) {

        int id = Integer.parseInt(request.queryParams("id"));
            
        UserDAO.delete(id);

        response.redirect("http://localhost:5500/projeto-alfa/src/resources/adm.html");

        return "";
    }

    public static Object update(Request request, Response response) {

        int id = Integer.parseInt(request.queryParams("id"));
        String user = request.queryParams("user");
        String email = request.queryParams("email");
        String password = request.queryParams("password");
            
        UserDAO.update(id, user, email, password);

        response.redirect("http://localhost:5500/projeto-alfa/src/resources/adm.html");

        return "";

    }

    public static String get(Request request, Response response) {

        int userId = Integer.parseInt(request.queryParams("userId"));

        return UserDAO.get(userId);

    }

    public static String getAll(Request request, Response response) {

        return UserDAO.getAll();

    }

    public static int login(Request request, Response response) {

        String user = request.queryParams("user");
        String password = request.queryParams("password");

        return UserDAO.login(user, password);

    }


}
