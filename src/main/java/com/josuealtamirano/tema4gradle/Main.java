package com.josuealtamirano.tema4gradle;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// El TOKEN no es necesario para interactuar con modelos locales
        final String TOKEN = "no-hace-falta";
        var modelA = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.1:8b")
                .build();
        var modelB = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.1:8b")
                .build();

        
        List<ChatMessage> historyA = new ArrayList<>();
        List<ChatMessage> historyB = new ArrayList<>();


        historyA.add(new UserMessage("Eres un entrevistador curioso que hace preguntas sobre el futuro de la tecnología"));
        historyB.add(new UserMessage("Eres un experto en física cuántica."));

        // Intereacción 1
        historyA.add(new UserMessage("Genera una pregunta corta para un experto sobre el futuro"));
        AiMessage preguntaDeEntrevistador = modelA.generate(historyA).content();
        historyA.add(preguntaDeEntrevistador);
        System.out.println("Entrevistador: " + preguntaDeEntrevistador.text());

        // Interacción 2
        historyB.add(new UserMessage(preguntaDeEntrevistador.text()));
        AiMessage respuestaExperto = modelB.generate(historyB).content();
        historyB.add(respuestaExperto);

        System.out.println("\nExperto en física: " + respuestaExperto.text());
    }
}