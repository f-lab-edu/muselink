package com.muselink.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String email;
    private String password;
    private String username;
    private String profileImageUrl;
    private String bio;
}
