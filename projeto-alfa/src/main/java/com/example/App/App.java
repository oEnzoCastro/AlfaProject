package com.example.App;

import static spark.Spark.*;

import java.sql.Connection;

import com.example.DAO.DAO;
import com.example.Services.userService;

public class App {
    public static void main(String[] args) {

        @SuppressWarnings("unused")
        Connection connection = DAO.getConnection();

        userService.createTable();

        post("/inserirUsuario", (request, response) -> userService.add(request, response));
        post("/atualizarUsuario", (request, response) -> userService.update(request, response));
        post("/removerUsuario", (request, response) -> userService.delete(request, response));

        get("/getAll", (request, response) -> {
            userService.getAll(request, response);
            response.redirect("http://youtube.com");
            return "";
        });
    }
}
