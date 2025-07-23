package com.muselink.backend.domain.user;

import com.muselink.backend.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

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

    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {

                return jwtTokenProvider.generateToken(user.getUsername());
            }
        }
        throw new RuntimeException("Invalid email or password");
    }
}
