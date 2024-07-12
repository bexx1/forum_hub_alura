package com.example.alura.forum.domain.user.dto;

import com.example.alura.forum.domain.user.User;

public record UserDetailsDTO(Long id, String name) {
    public UserDetailsDTO(User user) {
        this(user.getId(), user.getName());
    }
}
