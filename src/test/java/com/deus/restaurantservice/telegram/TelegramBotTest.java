package com.deus.restaurantservice.telegram;

import com.deus.restaurantservice.service.ReservationService;
import com.deus.restaurantservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TelegramBotTest {
    @Mock
    private UserService userService;

    @Mock
    private ReservationService reservationService;

    private TelegramBot telegramBot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        telegramBot = new TelegramBot(userService, reservationService);
    }

    @Test
    public void testGetBotUsername() {
        assertEquals("restaurant_deus_bot", telegramBot.getBotUsername());
    }

    @Test
    public void testGetBotToken() {
        assertEquals(System.getenv("token"), telegramBot.getBotToken());
    }
}