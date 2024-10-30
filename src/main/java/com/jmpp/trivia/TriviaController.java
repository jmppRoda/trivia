package com.jmpp.trivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmpp.trivia.entities.Categoria;
import com.jmpp.trivia.entities.ChatGptClient;
import com.jmpp.trivia.entities.Pregunta;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TriviaController {

    @GetMapping("/question/{categoria}")
    public String getQuestion(@PathVariable String categoria){

        ChatGptClient chatGpt = new ChatGptClient();
        String respuestaJson = chatGpt.enviarPregunta("Estoy montando un trivial, necesito que me des una pregunta de la categoría " + categoria + ", con la siguiente estructura JSON:\n" +
                "Es imprescindible que tu respuesta solo contenga código JSON sin ninguna aclaracion que no forme parte del JSON y la respuesta correcta no tiene que ser siempre la primera, tiene que variar aleatoriamente\n" +
                "\n" +
                "{\n" +
                "    \"category\": \"Aquí va el nombre de la categoría\",\n" +
                "    \"question\": \"Aquí va la pregunta\",\n" +
                "    \"options\": [\n" +
                "        \"Aquí va la opción 1\",\n" +
                "        \"Aquí va la opción 2\",\n" +
                "        \"Aquí va la opción 3\"\n" +
                "    ],\n" +
                "    \"answer\": Aquí tienes que poner la respuesta correcta en caso de ser la primera es el numero 0,\n" +
                "    \"explanation\": \"Aquí tienes que poner una explicacion de por qué es la respuesta correcta\"\n" +
                "}");
        return respuestaJson;

//        ObjectMapper convertidor = new ObjectMapper();
//        Pregunta preg = convertidor.convertValue(respuestaJson, Pregunta.class);
//        return preg;
    }

    @GetMapping("/categories")
    public Categoria[] getCategorias(){

        Categoria cat = new Categoria();
        cat.setCategory("Arte");
        cat.setDescription("Preguntas realcionadas con arte, literatura, música y otras expresiones culturales");

        Categoria cat2 = new Categoria();
        cat2.setCategory("Deportes");
        cat2.setDescription("Preguntas realcionadas con diversos deportes y eventos deportivos");

        Categoria[] categorias = new Categoria[2];
        categorias[0] = cat;
        categorias[1] = cat2;

        return categorias;

        /*return "[\n" +
                "  {\n" +
                "    \"category\": \"Cultura\",\n" +
                "    \"description\": \"Preguntas relacionadas con arte, literatura, música y otras expresiones culturales.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"category\": \"Deportes\",\n" +
                "    \"description\": \"Preguntas relacionadas con diversos deportes y eventos deportivos.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"category\": \"Arte\",\n" +
                "    \"description\": \"Preguntas relacionadas con pintura, escultura, arquitectura y otras formas de expresión artística.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"category\": \"Cine\",\n" +
                "    \"description\": \"Preguntas relacionadas con películas, directores, actores y otros aspectos del cine.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"category\": \"Historia\",\n" +
                "    \"description\": \"Preguntas relacionadas con eventos históricos, personajes y períodos importantes.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"category\": \"Ciencia\",\n" +
                "    \"description\": \"Preguntas relacionadas con diferentes ramas de la ciencia, descubrimientos y avances científicos.\"\n" +
                "  }\n" +
                "]";*/
    }
}
