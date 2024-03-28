package com.deus.restaurantservice.telegram;

import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.deus.restaurantservice.utils.DateTimeUtils.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME = "restaurant_deus_bot";
    private final String BOT_TOKEN = System.getenv("token");
    private final UserService userService;
    private final ReservationService reservationService;

    public TelegramBot(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
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
        var chatId = update.getMessage().getChatId();
        var userMessage = update.getMessage();
        sendButtonMessage(chatId);
        var userTgName = userMessage.getFrom().getUserName();
        var user = getUser(userTgName);
        if (Objects.isNull(user)) {
            sendMessage(chatId, "Пользователь " + userTgName + " не зарегистрирован");
        } else {
            if (update.getMessage().getText().equals("Мои бронирования")) {
                printUserReservations(user, chatId);
            } else if (update.getMessage().getText().equals("Проверить регистрацию")) {
                sendMessage(chatId, checkUserExist(userTgName));
            }
        }
    }

    private User getUser(String userTgName) {
        return userService.findByTelegram(userTgName);
    }

    private void sendButtonMessage(Long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton userExistButton = new KeyboardButton("Проверить регистрацию");
        KeyboardButton userReservationButton = new KeyboardButton("Мои бронирования");

        keyboardFirstRow.add(userExistButton);
        keyboardFirstRow.add(userReservationButton);

        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите одну из кнопок:");
        message.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void printUserReservations(User user, Long chatId) {
        var reservations = reservationService.getAllReservationByUser(user);
        for (Reservation reservation : reservations) {
            sendMessage(chatId, "Ресторан: " + reservation.getTable().getRestaurant().getAddress() +
                    " Дата " + getDateFromDateTime(reservation.getDateTime()) +
                    " Время " + getTimeFromDateTime(reservation.getDateTime()));
        }
    }

    private String checkUserExist(String userTgName) {
        var userList = userService.getAllUser();
        var user = userService.findByTelegram(userTgName);
        return userList.contains(user) ? "Вы зарегистрированы" : "Вы не зарегистрированы";
    }

    private void sendMessage(Long idChat, String message) {
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
