package com.example.Services;

import com.example.DAO.ModuloDAO;

import spark.Request;
import spark.Response;

public class ModuloService {

    public static Object createTable() {

        ModuloDAO.createTable();

        return "";

    }

    public static Object add(Request request, Response response) {
        ModuloDAO.add(request.queryParams("moduloName"));
        return "";
    }

    public static String getAll(Request request, Response response) {

        return ModuloDAO.getAll();

    }

    public static Object delete(Request request, Response response) {

        int id = Integer.parseInt(request.queryParams("deleteIdModulo"));
            
        ModuloDAO.delete(id);

        response.redirect("http://127.0.0.1:5500/projeto-alfa/src/resources/adm.html");

        return "";
    }
    
    public static Object update(Request request, Response response) {

        int id = Integer.parseInt(request.queryParams("moduloid"));
        String user = request.queryParams("nameModulo");
            
        ModuloDAO.update(id, user);

        response.redirect("http://127.0.0.1:5500/projeto-alfa/src/resources/adm.html");

        return "";

    }

}
