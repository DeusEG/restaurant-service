package com.deus.restaurantservice.security;

import com.deus.restaurantservice.model.User;
import com.deus.restaurantservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByTelegram(username);
        if (user == null) {
            throw new UsernameNotFoundException("Не найдет пользователь с именем: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getTelegram())
                .password(user.getPassword())
                .roles(user.getRole().getName())
                .build();
    }
}