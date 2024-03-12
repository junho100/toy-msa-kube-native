package com.example.userservice.domain.user.model.command;

import com.example.userservice.domain.auth.model.command.SignUpCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserCommand {
    private String email;
    private String password;
    private String name;
}
