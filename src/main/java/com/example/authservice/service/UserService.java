package com.example.authservice.service;

import com.example.authservice.domain.Role;
import com.example.authservice.domain.dto.UserDto;
import com.example.authservice.domain.model.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public User register(UserDto userDto) throws Exception {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new Exception("Email is already taken");
        }

        User user = User.builder()
            .role(Role.USER)
            .password(userDto.getPassword())
            .name(userDto.getName())
            .email(userDto.getEmail())
            .build();

        user.setEmail(userDto.getEmail());

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    public String login(UserDto request) {

        User user = findByEmailAndPassword(request.getEmail(), request.getPassword());

        if (user == null) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return jwtProvider.generateToken(user.getEmail(), user.getName());
    }

    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);

        log.info("IN findByEmail - user: {} found by email: {}", result, email);

        return result;
    }

    public User findByEmailAndPassword(String email, String password) {
        User result = userRepository.findByEmail(email);

        if (result != null) {
            if (password.equals(result.getPassword())) {
                log.info("IN findByEmailAndPassword - user: {} found", result);

                return result;
            }
        }

        return null;
    }
}
