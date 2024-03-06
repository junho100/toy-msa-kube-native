package com.example.userservice.domain.auth.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRes {
    private String token;
    private boolean isSuccess;
}
