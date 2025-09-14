package com.muselink.backend.global.kafka;

import com.muselink.backend.domain.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "post-created";

    public void sendPostCreatedEvent(PostCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
