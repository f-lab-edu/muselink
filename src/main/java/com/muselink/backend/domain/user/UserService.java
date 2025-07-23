package com.muselink.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isEmailDuplicated(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isUsernameDuplicated(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(User user) {

        if (isEmailDuplicated(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (isUsernameDuplicated(user.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }

        userRepository.save(user);
    }
}
