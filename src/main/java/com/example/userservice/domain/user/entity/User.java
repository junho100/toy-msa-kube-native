package com.example.userservice.domain.user.entity;

import com.example.userservice.domain.user.model.command.CreateUserCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;

    @Column(nullable = false)
    private String name;

    @Builder
    public User (String email, String encryptedPassword, String name) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.name = name;
    }
}
