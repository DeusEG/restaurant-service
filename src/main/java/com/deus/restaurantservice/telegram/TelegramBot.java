package com.deus.restaurantservice.telegram;

import com.deus.restaurantservice.model.Restaurant;
import com.deus.restaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String BOT_NAME;
    @Value("${bot.token}")
    private String BOT_TOKEN;
    private final RestaurantService restaurantService;

    public TelegramBot(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    @Override
    public void onUpdateReceived(Update update) {
        var originalMessage = update.getMessage();
        Long idChat = update.getMessage().getChatId();
        String userName = originalMessage.getFrom().getUserName();
        System.out.println(userName);


        sendMessage(idChat, "Пользователь " + userName + " не зарегистрирован");
    }

    public void sendMessage(Long idChat, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(idChat);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
