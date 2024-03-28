package com.deus.restaurantservice.service;

import com.deus.restaurantservice.exception.IncorrectRegistrationData;
import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.RoleRepository;
import com.deus.restaurantservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final static String INCORRECT_TELEGRAM_ERROR_MESSAGE = "Укажите телеграм без символа '@'";
    private final static String SHORT_PASSWORD_ERROR_MESSAGE = "Ваш пароль слишком короткий";
    private final static Integer PASSWORD_MIN_LENGTH = 5;
    private static final String USER_ROLE = "USER";
    private static final String ADDRESS_SYMBOL = "@";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, RoleRepository roleRepository1, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository1;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User findByTelegram(String telegram) {
        return userRepository.findByTelegram(telegram);
    }


    @Transactional
    public String deleteByTelegram(String telegram) {
        return userRepository.removeByTelegram(telegram);
    }

    public User createUser(String name, String telegram, String password) {
        if (telegram.contains(ADDRESS_SYMBOL)) {
            throw new IncorrectRegistrationData(INCORRECT_TELEGRAM_ERROR_MESSAGE);
        } else if (password.length() < PASSWORD_MIN_LENGTH) {
            throw new IncorrectRegistrationData(SHORT_PASSWORD_ERROR_MESSAGE);
        }
        var user =  new User(name, telegram, roleRepository.findByName(USER_ROLE),
                passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }
}
