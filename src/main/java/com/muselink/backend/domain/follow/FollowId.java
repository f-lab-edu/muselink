package com.muselink.backend.domain.follow;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FollowId implements Serializable {
    private int follower;
    private int followee;
}
