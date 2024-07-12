package com.example.alura.forum.controller;

import com.example.alura.forum.domain.topic.Status;
import com.example.alura.forum.domain.topic.Topic;
import com.example.alura.forum.domain.topic.TopicRepository;
import com.example.alura.forum.domain.topic.dto.RegisterTopicDTO;
import com.example.alura.forum.domain.user.User;
import com.example.alura.forum.domain.user.UserRepository;
import com.example.alura.forum.infra.security.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicController {
    @Autowired
    private TopicRepository repository;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public void register(@RequestBody @Valid RegisterTopicDTO registerTopicDTO, HttpServletRequest request) {
        var token = request.getHeader("Authorization").replace("Bearer ", "");
        var subject = tokenService.getSubject(token);
        User author = (User) userRepository.findByUsername(subject);

        repository.save(new Topic(registerTopicDTO.title(), registerTopicDTO.message(), LocalDateTime.now(), author, Status.ACTIVE, registerTopicDTO.course()));
    }
}
