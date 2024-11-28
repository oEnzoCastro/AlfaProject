package com.example.App;

import static spark.Spark.*;

import java.sql.Connection;

import com.example.DAO.DAO;
import com.example.DAO.ImageAnalysisDAO;
import com.example.Services.AulaService;
import com.example.Services.ModuloService;
import com.example.Services.UserService;

public class App {
    public static void main(String[] args) {

        @SuppressWarnings("unused")
        Connection connection = DAO.getConnection();

        UserService.createTable(); // Cria Table Usuario
        AulaService.createTable(); // Cria Table Aula
        ModuloService.createTable(); // Cria Table Modulo

        // Usuário

        post("/inserirUsuario", (request, response) -> UserService.add(request, response));
        post("/atualizarUsuario", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return UserService.update(request, response);
        });
        post("/removerUsuario", (request, response) -> UserService.delete(request, response));
        post("/loginUsuario", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return UserService.login(request, response);
        });
        post("/getUsuario", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return UserService.get(request, response);
        });
        post("/getAll", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return UserService.getAll(request, response);
        });

        // Modulos

        post("/inserirModulo", (request, response) -> ModuloService.add(request, response));
        post("/atualizarModulo", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return ModuloService.update(request, response);
        });
        post("/getAllModulos", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return ModuloService.getAll(request, response);
        });
        post("/removerModulo", (request, response) -> ModuloService.delete(request, response));

        // Aulas

        post("/getAulas", (request, response) -> {

            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS

            return AulaService.getAulas(request, response);
        });
        post("/inserirAula", (request, response) -> AulaService.add(request, response));
        post("/removerAula", (request, response) -> AulaService.delete(request, response));
        post("/showImgText", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return ImageAnalysisDAO.get(request, response);
        });
        post("/atualizarAula", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Libera acesso ao JS
            return AulaService.update(request, response);
        });
    
    
    }
}
