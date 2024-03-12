package com.example.userservice.domain.auth.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpReq {
    private String email;
    private String password;
    private String name;
}
