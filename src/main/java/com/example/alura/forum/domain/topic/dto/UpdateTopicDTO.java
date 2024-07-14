package com.example.alura.forum.domain.topic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateTopicDTO(
        @NotNull
        Long id,
        String title,
        String message,
        String course
        ) {
}
