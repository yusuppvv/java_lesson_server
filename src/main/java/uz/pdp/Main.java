package uz.pdp;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        System.out.println("running ...");
        try {
            botsApi.registerBot(new MyBot("7810653586:AAGMFoF9f-zKV0c--138n72TXjsNDv2pV5s"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
