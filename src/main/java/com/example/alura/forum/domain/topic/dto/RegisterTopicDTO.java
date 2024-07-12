package com.example.alura.forum.domain.topic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RegisterTopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String course
        ) {
}
