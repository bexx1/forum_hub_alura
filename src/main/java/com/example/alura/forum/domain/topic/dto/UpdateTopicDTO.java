package com.example.alura.forum.domain.topic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateTopicDTO(
        @NotNull
        Long id,
        @NotBlank(message = "The title is mandatory")
        String title,
        @NotBlank(message = "The message is mandatory")
        String message,
        @NotBlank(message = "The course is mandatory")
        String course
        ) {
}
