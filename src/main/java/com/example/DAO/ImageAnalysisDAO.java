package com.example.DAO;

import java.util.ArrayList;
import java.util.Arrays;

import com.azure.ai.vision.imageanalysis.ImageAnalysisClient;
import com.azure.ai.vision.imageanalysis.ImageAnalysisClientBuilder;
import com.azure.ai.vision.imageanalysis.models.DetectedTextLine;
import com.azure.ai.vision.imageanalysis.models.ImageAnalysisResult;
import com.azure.ai.vision.imageanalysis.models.VisualFeatures;
import com.azure.core.credential.KeyCredential;

import spark.Request;
import spark.Response;

public class ImageAnalysisDAO {

    //String endpoint = System.getenv("VISION_ENDPOINT");
    //String key = System.getenv("VISION_KEY");

    public static String get(Request request, Response response) {
        
        String endpoint = "https://readeria.cognitiveservices.azure.com/";
        String key = "FrWqicWxttgqyG3mocDD3bJIwCRKmbfN2FblRAvBy9KTNT7lUIO9JQQJ99AKACHYHv6XJ3w3AAAFACOGLBbO";
        ArrayList<String> arrayList = new ArrayList<String>();
        
        if (endpoint == null || key == null) {
            System.out.println("Missing environment variable 'VISION_ENDPOINT' or 'VISION_KEY'.");
            System.out.println("Set them before running this sample.");
            System.exit(1);
        }
        
        // Create a synchronous client using API key authentication
        ImageAnalysisClient client = new ImageAnalysisClientBuilder()
        .endpoint(endpoint)
        .credential(new KeyCredential(key))
        .buildClient();
    
/*
        ImageAnalysisResult result = client.analyzeFromUrl(
            request.queryParams("fileUrl"), // imageUrl: the URL of the image to analyze
        Arrays.asList(VisualFeatures.READ), // visualFeatures
            null); 
*/

        String url = request.queryParams("imageURL");
        System.out.println(url);

        ImageAnalysisResult result = client.analyzeFromUrl(
            url, // imageUrl: the URL of the image to analyze
        Arrays.asList(VisualFeatures.READ), // visualFeatures
            null);

        // Print analysis results to the console
        System.out.println(" Read:");
        for (DetectedTextLine line : result.getRead().getBlocks().get(0).getLines()) {
            arrayList.add(line.getText());
            System.out.println("   Line: " + line.getBoundingPolygon());
        }

        String text = "";

        int i = 0;

        for (String string : arrayList) {

            System.out.println(string);
            text += "<p id=\"line_" + i + "\">" + string + "</p>";

            i++;
        }

        return text;
    }
}

