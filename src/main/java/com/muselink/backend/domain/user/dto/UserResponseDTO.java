package com.muselink.backend.domain.user.dto;

import com.muselink.backend.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private String email;
    private String username;
    private String profileImageUrl;
    private String bio;

    public UserResponseDTO(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.profileImageUrl = user.getProfileImageUrl();
        this.bio = user.getBio();
    }
}
