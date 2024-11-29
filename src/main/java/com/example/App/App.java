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

        // CORS
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*"); // Change * to your frontend domain for production
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
            response.header("Access-Control-Allow-Credentials", "true");
        });

        // Usuário

        post("/inserirUsuario", (request, response) -> UserService.add(request, response));
        post("/atualizarUsuario", (request, response) -> {
            return UserService.update(request, response);
        });
        post("/removerUsuario", (request, response) -> UserService.delete(request, response));
        post("/loginUsuario", (request, response) -> {
            return UserService.login(request, response);
        });
        post("/getUsuario", (request, response) -> {
            return UserService.get(request, response);
        });
        post("/getAll", (request, response) -> {
            return UserService.getAll(request, response);
        });

        // Modulos

        post("/inserirModulo", (request, response) -> ModuloService.add(request, response));
        post("/atualizarModulo", (request, response) -> {
            return ModuloService.update(request, response);
        });
        post("/getAllModulos", (request, response) -> {
            return ModuloService.getAll(request, response);
        });
        post("/removerModulo", (request, response) -> ModuloService.delete(request, response));

        // Aulas

        post("/getAulas", (request, response) -> {
            return AulaService.getAulas(request, response);
        });
        post("/inserirAula", (request, response) -> AulaService.add(request, response));
        post("/removerAula", (request, response) -> AulaService.delete(request, response));
        post("/atualizarAula", (request, response) -> {
            return AulaService.update(request, response);
        });
        post("/showImgText", (request, response) -> {
            return ImageAnalysisDAO.get(request, response);
        });
    
    
    }
}
