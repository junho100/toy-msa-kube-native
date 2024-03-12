package com.example.userservice.domain.auth.model.command;

import com.example.userservice.domain.auth.model.request.SignUpReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpCommand {
    private String email;
    private String password;
    private String name;

    public static SignUpCommand from(SignUpReq signUpReq) {
        return SignUpCommand.builder()
            .email(signUpReq.getEmail())
            .password(signUpReq.getPassword())
            .name(signUpReq.getName())
            .build();
    }
}
