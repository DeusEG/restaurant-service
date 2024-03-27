package com.deus.restaurantservice.telegram;

import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.UserService;
import com.deus.restaurantservice.utils.DateTimeUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        var originalMessage = update.getMessage();
        Long idChat = update.getMessage().getChatId();
        String userTgName = originalMessage.getFrom().getUserName();
        User user = checkUserExist(originalMessage);
        List<Reservation> reservations = reservationService.getAllReservationByUser(user);
        if (Objects.isNull(user)) {
            sendMessage(idChat, "Пользователь " + userTgName + " не зарегистрирован");
        } else {
            if (update.getMessage().getText().equals("Брони")) {
                for (Reservation reservation : reservations) {
                    sendMessage(idChat, "Ресторан: " + reservation.getTable().getRestaurant().getAddress() +
                            " Дата " + getDateFromDateTime(reservation.getDateTime()) +
                            " Время " + getTimeFromDateTime(reservation.getDateTime()));
                }
            }
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
