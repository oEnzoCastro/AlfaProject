package com.example.Services;

import com.example.DAO.AulaDAO;

import spark.Request;
import spark.Response;

public class AulaService {
    public static Object createTable() {

        AulaDAO.createTable();

        return "";
    }

    public static Object add(Request request, Response response) {

        String aulaLink = request.queryParams("aulaLink");
        String aulaName = request.queryParams("aulaName");
        int moduloAula = Integer.parseInt(request.queryParams("addAulaId"));
        Boolean checkAula = false;
        
        response.redirect("https://projeto-alfa.netlify.app/src/resources/adm.html");

        return AulaDAO.add(aulaLink, aulaName, moduloAula, checkAula);

    }

    public static Object delete(Request request, Response response) {

        int idAula = Integer.parseInt(request.queryParams("deleteIdAula"));
        
/* 
        if (request.url() == "http://127.0.0.1:5500/projeto-alfa/src/resources/adm.html") {
            response.redirect("http://127.0.0.1:5500/projeto-alfa/src/resources/adm.html");
        } else {
            response.redirect("http://127.0.0.1:5500/projeto-alfa/src/resources/perfil.html");
        }
*/

        return AulaDAO.deleteAula(idAula);

    }

    public static String getAulas(Request request, Response response) {
        
        int id = Integer.parseInt(request.queryParams("moduloId"));
        
        return AulaDAO.showAulas(id);

    }

    public static Object update(Request request, Response response) {

        int id = Integer.parseInt(request.queryParams("updateAulaId"));
        String name = request.queryParams("aulaName");
        String linkYoutube = request.queryParams("aulaLink");
        String numAula = request.queryParams("aulaNum");
            
        AulaDAO.update(id, numAula, name, linkYoutube);

        response.redirect("https://projeto-alfa.netlify.app/src/resources/adm.html");

        return "";

    }

}


