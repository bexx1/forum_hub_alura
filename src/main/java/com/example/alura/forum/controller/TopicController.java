package com.example.alura.forum.controller;

import com.example.alura.forum.domain.topic.Status;
import com.example.alura.forum.domain.topic.Topic;
import com.example.alura.forum.domain.topic.TopicRepository;
import com.example.alura.forum.domain.topic.dto.RegisterTopicDTO;
import com.example.alura.forum.domain.topic.dto.TopicDetailsDTO;
import com.example.alura.forum.domain.topic.dto.UpdateTopicDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity register(@RequestBody @Valid RegisterTopicDTO registerTopicDTO, HttpServletRequest request, UriComponentsBuilder uriBuilder) {
        var token = request.getHeader("Authorization").replace("Bearer ", "");
        var subject = tokenService.getSubject(token);
        User author = (User) userRepository.findByUsername(subject);

        Topic topic = new Topic(registerTopicDTO.title(), registerTopicDTO.message(), LocalDateTime.now(), author, Status.ACTIVE, registerTopicDTO.course());
        repository.save(topic);

        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsDTO(topic));
    }

    @GetMapping
    public ResponseEntity<List<TopicDetailsDTO>> list() {
        var topics = repository.findAll().stream().map(TopicDetailsDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var topic = repository.getReferenceById(id);
        return ResponseEntity.ok(new TopicDetailsDTO(topic));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateTopicDTO data) {
        var topic = repository.findById(data.id());
        if (topic.isPresent()) {
            var topicFound = topic.get();
            topicFound.update(data);

            return ResponseEntity.ok(new TopicDetailsDTO(topicFound));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var topic = repository.findById(id);
        if (topic.isPresent()) {
            repository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
