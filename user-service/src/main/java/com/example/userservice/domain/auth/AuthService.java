package com.example.userservice.domain.auth;

import com.example.userservice.common.utils.JwtService;
import com.example.userservice.domain.auth.model.command.SignUpCommand;
import com.example.userservice.domain.user.UserService;
import com.example.userservice.domain.user.entity.User;
import com.example.userservice.domain.user.model.command.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public void signUp(SignUpCommand signUpCommand) {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
            .email(signUpCommand.getEmail())
            .password(passwordEncoder.encode(signUpCommand.getPassword()))
            .name(signUpCommand.getName())
            .build();
        userService.createUser(createUserCommand);
    }

    public String login(String email, String password) {
        User user = userService.getUserByEmail(email);
        if (!passwordEncoder.matches(password, user.getEncryptedPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return jwtService.createJwtTokenByUserId(user.getId());
    }
}
