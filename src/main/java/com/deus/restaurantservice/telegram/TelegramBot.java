package com.deus.restaurantservice.telegram;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String BOT_NAME;
    @Value("${bot.token}")
    private String BOT_TOKEN;
    private final UserService userService;

    public TelegramBot(UserService userService) {
        this.userService = userService;
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
        String userTgName = originalMessage.getFrom().getUserName();
        System.out.println(userTgName);
        User user = checkUserExist(originalMessage);
        if (Objects.isNull(user)) {
            sendMessage(idChat, "Пользователь " + userTgName + " не зарегистрирован");
        } else {
            sendMessage(idChat, "Хей хо");
        }
    }


    private User checkUserExist(Message message) {
        String userTgName = message.getFrom().getUserName();
        var userList = userService.getAllUser();
        for (User user : userList) {
            if (user.getTelegram().equals(userTgName)) {
                return user;
            }
        }
        return null;
    }

    public void sendMessage(Long idChat, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(idChat);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }


}
