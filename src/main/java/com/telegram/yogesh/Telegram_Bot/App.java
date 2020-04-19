package com.telegram.yogesh.Telegram_Bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Telegram_Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    
}
