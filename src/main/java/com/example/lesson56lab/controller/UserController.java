package com.example.lesson56lab.controller;

import com.example.lesson56lab.dto.UserDTO;
import com.example.lesson56lab.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerClient(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

}
