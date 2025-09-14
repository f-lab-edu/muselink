package com.muselink.backend.domain.post;

import com.muselink.backend.domain.post.dto.CreatePostRequest;
import com.muselink.backend.domain.post.event.PostCreatedEvent;
import com.muselink.backend.domain.user.User;
import com.muselink.backend.domain.user.UserRepository;
import com.muselink.backend.global.kafka.PostEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostEventProducer postEventProducer;

    public void createPost(CreatePostRequest request) {

        User user = userRepository.findById(request.getUserId());

        Post post = Post.builder()
                .user(user)
                .content(request.getContent())
                .build();

        Post savedPost = postRepository.save(post);

        PostCreatedEvent event = new PostCreatedEvent(
                savedPost.getPostId(),
                user.getUserId(),
                user.getCreatedAt().toString()
        );

        postEventProducer.sendPostCreatedEvent(event);
    }
}
