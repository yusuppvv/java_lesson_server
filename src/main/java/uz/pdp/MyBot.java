package uz.pdp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyBot extends TelegramLongPollingBot {
    public MyBot(String botToken) {
        super(botToken);
    }

    private BotButtonService buttonService = new BotButtonService();

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String response = getAIResponse(text);
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(response);
            message.setParseMode(ParseMode.HTML);
            execute(message);
        }
    }

    private String getAIResponse(String userInput) {
        String text = """
                You are a multilingual paraphraser and language assistant. Analyze the user's input and follow these rules:
                
                1. If the input is a single word or phrase, generate the following:
                   - Translation: Provide the translation of the word or phrase into Uzbek.
                   - Synonyms: List synonyms of the word or phrase in English.
                   - Antonyms: List antonyms of the word or phrase in English.
                   - Example in Sentence: Use the word or phrase in a sentence in English.
                
                2. If the input is a full sentence or text, perform the following tasks:
                   - Paraphrase: Rewrite the sentence or text in fluent and natural English.
                   - Grammar and Spelling Corrections: Identify grammar and spelling mistakes, provide corrected text, and explain the corrections made.
                
                3. Additional Rule:
                   - If the user requests, adjust the tone or style of the paraphrased text (e.g., formal, informal, concise, expanded).
                
                Respond strictly based on these rules, and do not add any extra comments or explanations.
                
                 <b>User Input:</b> %s
                """.formatted(userInput);


        try {
            HttpClient client = HttpClient.newHttpClient();

            // Properly escape the text inside the JSON payload
            String jsonBody = "{\n" +
                              "  \"contents\": [\n" +
                              "    { \"parts\": [{ \"text\": \"" + text.replace("\"", "\\\"") + "\" }] }\n" +
                              "  ]\n" +
                              "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key="
                                    + "AIzaSyDqg2VTxbzAQrL6FyaeToz82Oe80MFN8rI"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            if (response.statusCode() == 200) {
                JsonElement json = JsonParser.parseString(response.body());
                JsonArray array = json.getAsJsonObject().getAsJsonArray("candidates");
                if (array != null && array.size() > 0) {
                    JsonObject candidate = array.get(0).getAsJsonObject();
                    JsonObject content = candidate.getAsJsonObject("content");
                    JsonArray parts = content.getAsJsonArray("parts");
                    if (parts != null && parts.size() > 0) {
                        JsonObject part = parts.get(0).getAsJsonObject();
                        return part.get("text").getAsString();
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "I think there's some problem. Because of that, I can't generate response. " +
               "Please contact with [Your contact info]";
    }


    @Override
    public String getBotUsername() {
        return "paraphrazeIt_bot";
    }
}
