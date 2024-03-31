package com.deus.restaurantservice.telegram;

import com.deus.restaurantservice.model.Reservation;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.UserService;
import com.deus.restaurantservice.service.impl.ReservationServiceImpl;
import com.deus.restaurantservice.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.deus.restaurantservice.utils.DateTimeUtils.*;

/**
 * Класс для работы с телеграм ботом
 */
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

    /**
     * Метод для получения имени бота
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    /**
     * Метод для получения токена бота
     *
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    /**
     * Метод для получения и обработки сообщений от пользователя
     *
     * @param update Данные поулчаемые от пользователя
     *
     */
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

    /**
     * Метод для создания и отображения кнопок в телеграм боте
     *
     * @param chatId  Идентификатор чата с пользователем
     *
     */
    private void sendButtonMessage(Long chatId) {
        var replyKeyboardMarkup = new ReplyKeyboardMarkup();
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

        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите одну из кнопок:");
        message.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createDateTimeKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        // Создаем кнопку для выбора даты
        List<InlineKeyboardButton> dateRow = new ArrayList<>();
        InlineKeyboardButton dateButton = new InlineKeyboardButton("Выбрать дату");
        dateButton.setCallbackData("date");
        dateRow.add(dateButton);
        rowList.add(dateRow);

        // Создаем кнопку для выбора времени
        List<InlineKeyboardButton> timeRow = new ArrayList<>();
        InlineKeyboardButton timeButton = new InlineKeyboardButton("Выбрать время");
        timeButton.setCallbackData("time");
        timeRow.add(timeButton);
        rowList.add(timeRow);

        keyboardMarkup.setKeyboard(rowList);
        return keyboardMarkup;
    }

    /**
     * Метод для отображения пользователю данных о его бронированиях
     *
     * @param chatId  Идентификатор чата с пользователем
     * @param user    Пользователь
     *
     */
    private void printUserReservations(User user, Long chatId) {
        var reservations = reservationService.getAllReservationByUser(user);
        for (Reservation reservation : reservations) {
            sendMessage(chatId, "Ресторан: " + reservation.getTable().getRestaurant().getAddress() +
                    " Дата " + getDateFromDateTime(reservation.getDateTime()) +
                    " Время " + getTimeFromDateTime(reservation.getDateTime()));
        }
    }

    /**
     * Метод для оповещения пользваотеля о его регистрации в системе
     *
     * @param userTgName  Телеграм пользователя
     * @return            Сообещние о состоянии регистрации в системе
     */
    private String checkUserExist(String userTgName) {
        var userList = userService.getAllUser();
        var user = userService.findByTelegram(userTgName);
        return userList.contains(user) ? "Вы зарегистрированы" : "Вы не зарегистрированы";
    }

    /**
     * Метод для отправки сообщений пользователю в чат
     *
     * @param idChat   Идентификатор чата
     * @param message  Содержание сообщения
     *
     */
    private void sendMessage(Long idChat, String message) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(idChat);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
