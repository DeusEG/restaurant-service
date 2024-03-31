package com.deus.restaurantservice.service.impl;

import com.deus.restaurantservice.exception.DeleteModerException;
import com.deus.restaurantservice.exception.IncorrectRegistrationDataException;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Сервис для работы с пользователями
 */
@Service
public class UserServiceImpl implements com.deus.restaurantservice.service.UserService {

    private final static String INCORRECT_TELEGRAM_ERROR_MESSAGE = "Укажите телеграм без символа '@'";
    private final static String SHORT_PASSWORD_ERROR_MESSAGE = "Ваш пароль слишком короткий";
    private final static String USERNAME_ERROR_MESSAGE = "Вы забыли ввести имя";
    private final static String EMPTY_TELEGRAM_ERROR_MESSAGE = "Поле телеграм не может быть пустым";
    private final static String DELETE_USER_ERROR = "Нельзя удалить пользователя с ролью MODER";
    private final static Integer PASSWORD_MIN_LENGTH = 5;
    private static final String USER_ROLE = "USER";
    private static final String MODER_ROLE = "MODER";
    private static final String ADDRESS_SYMBOL = "@";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findByTelegram(String telegram) {
        return userRepository.findByTelegram(telegram);
    }

    @Override
    @Transactional
    public Integer deleteByTelegram(String telegram) {
        var user = findByTelegram(telegram);
        if (user.getRole().getName().equals(MODER_ROLE)) {
            throw new DeleteModerException(DELETE_USER_ERROR);
        }
        return userRepository.removeByTelegram(telegram);
    }

    @Override
    @Transactional
    public User updateUserInfo(User user, String userName, String password) {
        if (userName.isEmpty()) {
            throw new IncorrectRegistrationDataException(USERNAME_ERROR_MESSAGE);
        }
        if (password.isEmpty()) {
            throw new IncorrectRegistrationDataException(SHORT_PASSWORD_ERROR_MESSAGE);
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setName(userName);
        userRepository.save(user);
        return user;
    }

    @Override
    public User createUser(String name, String telegram, String password) {
        if (telegram == null) {
            throw new IncorrectRegistrationDataException(EMPTY_TELEGRAM_ERROR_MESSAGE);
        } else if (telegram.contains(ADDRESS_SYMBOL)) {
            throw new IncorrectRegistrationDataException(INCORRECT_TELEGRAM_ERROR_MESSAGE);
        } else if (password == null || password.length() < PASSWORD_MIN_LENGTH) {
            throw new IncorrectRegistrationDataException(SHORT_PASSWORD_ERROR_MESSAGE);
        }
        var user = new User(name, telegram, roleRepository.findByName(USER_ROLE),
                passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

}
