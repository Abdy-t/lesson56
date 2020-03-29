package com.example.lesson56lab.service;

import com.example.lesson56lab.dto.UserDTO;
import com.example.lesson56lab.model.User;
import com.example.lesson56lab.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).get();
    }

    public UserDTO register(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            var user = User.builder()
                    .name("Try again")
                    .email("A client with " + userDTO.getEmail() + " email already exists")
                    .password("***")
                    .build();
            return UserDTO.from(user);
        } else {
            var user = User.builder()
                    .name(userDTO.getName())
                    .email(userDTO.getEmail())
                    .password(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                    .build();
            userRepository.save(user);
            return UserDTO.from(user);
        }
    }
}
