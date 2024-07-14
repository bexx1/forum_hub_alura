package com.example.alura.forum.domain.topic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RegisterTopicDTO(
        @NotBlank(message = "The title is mandatory")
        String title,
        @NotBlank(message = "The message is mandatory")
        String message,
        @NotBlank(message = "The course is mandatory")
        String course
        ) {
}
