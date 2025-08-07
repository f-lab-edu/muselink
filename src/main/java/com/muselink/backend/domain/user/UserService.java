package com.muselink.backend.domain.user;

import com.muselink.backend.domain.user.dto.SignupRequest;
import com.muselink.backend.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public boolean isEmailDuplicated(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isUsernameDuplicated(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(SignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .username(request.getUsername())
                .profileImageUrl(request.getProfileImageUrl())
                .bio(request.getBio())
                .build();

        userRepository.save(user);
    }

    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {

                return jwtTokenProvider.generateToken(user.getUsername());
            }
        }
        throw new RuntimeException("Invalid email or password");
    }
}
