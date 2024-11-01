package com.example.App;

import static spark.Spark.*;

import java.sql.Connection;

import com.example.DAO.DAO;
import com.example.Services.UserService;

public class App {
    public static void main(String[] args) {

        @SuppressWarnings("unused")
        Connection connection = DAO.getConnection();

        UserService.createTable();

        post("/inserirUsuario", (request, response) -> UserService.add(request, response));
        post("/atualizarUsuario", (request, response) -> UserService.update(request, response));
        post("/removerUsuario", (request, response) -> UserService.delete(request, response));

        post("/loginUsuario", (request, response) -> UserService.login(request, response));

        post("/getAll", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return UserService.getAll(request, response);
        });
    }
}
