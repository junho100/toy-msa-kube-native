package com.example.userservice.domain.user;

import com.example.userservice.domain.user.entity.User;
import com.example.userservice.domain.user.model.command.CreateUserCommand;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(CreateUserCommand createUserCommand) {
        User user = User.builder()
                .email(createUserCommand.getEmail())
                .encryptedPassword(createUserCommand.getPassword())
                .name(createUserCommand.getName())
                .build();
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return user.get();
    }
}
