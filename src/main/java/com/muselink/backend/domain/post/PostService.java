package com.muselink.backend.domain.post;

import com.muselink.backend.domain.post.dto.CreatePostRequest;
import com.muselink.backend.domain.user.User;
import com.muselink.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void createPost(CreatePostRequest request) {

        User user = userRepository.findById(request.getUserId());

        Post post = Post.builder()
                .user(user)
                .content(request.getContent())
                .build();

        postRepository.save(post);
    }
}
