package com.telegram.yogesh.Telegram_Bot;



import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Telegram_Bot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

        Update_Message update_message = new Update_Message();
        SendMessage message = null;
        message = update_message.sendMessage(update);
        

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Yogesh Bot";
    }

    @Override
    public String getBotToken() {
        return "1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI";
    }
}
