package com.muselink.backend.domain.user;

import com.muselink.backend.domain.user.dto.LoginRequest;
import com.muselink.backend.domain.user.dto.UserResponseDTO;
import com.muselink.backend.domain.user.dto.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User created successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            String token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok().body(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyInfo(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateMyInfo(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserUpdateDTO updateDTO) {
        userService.updateUser(userDetails.getUser().getUserId(), updateDTO);
        return ResponseEntity.ok("User updated successfully!");
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyAccount(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.deleteUser(userDetails.getUser().getUserId());
        return ResponseEntity.ok("User deleted Successfully!");
    }
}
