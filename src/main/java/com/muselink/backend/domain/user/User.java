package com.muselink.backend.domain.user;

import com.muselink.backend.domain.common.Auditable;
import com.muselink.backend.domain.user.dto.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    private String profileImageUrl;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private LocalDateTime deletedAt;

    public void update(UserUpdateDTO dto) {
        this.username = dto.getUsername();
        this.profileImageUrl = dto.getProfileImageUrl();
        this.bio = dto.getBio();
    }
}
