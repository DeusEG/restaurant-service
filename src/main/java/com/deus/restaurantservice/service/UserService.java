package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.DeleteModerException;
import com.deus.restaurantservice.exception.IncorrectRegistrationDataException;
import com.deus.restaurantservice.model.User;

import java.util.List;

public interface UserService {

    /**
     * Метод для удаления пользователя по его телеграму
     *
     * @param telegram Телеграм пользователя
     * @return         Телеграм пользователя
     * @throws DeleteModerException если пытаются удалить пользователя с ролью MODER
     */
    Integer deleteByTelegram(String telegram);

    /**
     * Метод для создания пользователя
     *
     * @param name      Имя пользователя
     * @param telegram  Телеграм пользователя
     * @param password  Пароль пользователя
     * @return          Объект типа User
     * @throws IncorrectRegistrationDataException если некорректно указаны данные при регистрации
     */
    User createUser(String name, String telegram, String password);

    /**
     * Метод для получения всех пользователей
     *
     * @return Список всех пользователей
     */
    List<User> getAllUser();

    /**
     * Метод для поиска пользователя по его телеграму
     *
     * @param telegram    Телеграм пользователя
     * @return            Пользователь
     */
    User findByTelegram(String telegram);

    /**
     * Метод для изменения имени и пароля пользователя
     *
     * @param user     Пользователь
     * @param userName Имя пользователя
     * @param password Пароль пользователя
     * @return         Объект типа User
     * @throws IncorrectRegistrationDataException если некорректно указаны данные при изменение
     */
    User updateUserInfo(User user, String userName, String password);
}
