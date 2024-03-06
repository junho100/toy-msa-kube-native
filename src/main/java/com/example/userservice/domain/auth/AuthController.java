package com.example.userservice.domain.auth;

import com.example.userservice.domain.auth.model.command.SignUpCommand;
import com.example.userservice.domain.auth.model.request.SignUpReq;
import com.example.userservice.domain.auth.model.response.SignUpRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpRes> signUp(@RequestBody SignUpReq signUpReq) {
        SignUpCommand signUpCommand = SignUpCommand.from(signUpReq);
        authService.signUp(signUpCommand);

        String token = authService.login(signUpReq.getEmail(), signUpReq.getPassword());

        SignUpRes signUpRes = SignUpRes.builder()
            .token(token)
            .build();

        return new ResponseEntity<SignUpRes>(signUpRes, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SignUpRes> login(@RequestBody SignUpReq signUpReq) {
        String token = authService.login(signUpReq.getEmail(), signUpReq.getPassword());

        SignUpRes signUpRes = SignUpRes.builder()
            .token(token)
            .build();

        return new ResponseEntity<SignUpRes>(signUpRes, HttpStatus.CREATED);
    }
}
