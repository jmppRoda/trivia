package com.jmpp.trivia.entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGptClient {

    public String enviarPregunta(String pregunta) {

        String apiUrl = "https://api.openai.com/v1/chat/completions"; // URL para gpt-3.5-turbo
        String apiKey = "mi_apiKey"; // Reemplaza con tu clave de API
        String respuestaDeChatGpt = "";

        try {
            // Crear conexión HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Cuerpo de la solicitud JSON
            String jsonInputString = "{"
                    + "\"model\": \"gpt-3.5-turbo\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"¿Cuál es la capital de Francia?\"}]"
                    + "}";

            // Enviar el JSON en el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            int responseCode = connection.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta exitosa
                StringBuilder response = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
                System.out.println("Respuesta: " + response.toString());
                respuestaDeChatGpt = response.toString();
            } else {
                // Leer la respuesta de error
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String errorLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine.trim());
                }
                System.out.println("Error: " + errorResponse.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuestaDeChatGpt;

    }
}
