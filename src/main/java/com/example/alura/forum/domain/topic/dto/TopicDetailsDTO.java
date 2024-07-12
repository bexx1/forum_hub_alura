package com.example.alura.forum.domain.topic.dto;

import com.example.alura.forum.domain.topic.Status;
import com.example.alura.forum.domain.topic.Topic;
import com.example.alura.forum.domain.user.User;
import com.example.alura.forum.domain.user.dto.UserDetailsDTO;

import java.time.LocalDateTime;

public record TopicDetailsDTO(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Status status,
        UserDetailsDTO author,
        String course
) {

    public TopicDetailsDTO(Topic topic, UserDetailsDTO userDetails) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), userDetails, topic.getCourse());
    }
}
