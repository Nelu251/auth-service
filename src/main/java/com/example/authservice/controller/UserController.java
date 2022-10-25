package com.example.authservice.controller;

import com.example.authservice.domain.dto.UserDto;
import com.example.authservice.service.UserService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(HttpServletRequest request, @RequestBody UserDto userDto) throws Exception {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        UserDto newUser = UserDto.builder().email(email).password(password).name(userDto.getName()).build();

        userService.register(newUser);

        request.login(email, password);

        return this.login(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
