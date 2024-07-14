package com.example.alura.forum.domain.topic;

import com.example.alura.forum.domain.user.User;
import com.example.alura.forum.domain.user.dto.UserDetailsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Column(unique = true)
    private String message;
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private String course;

    public Topic(String title, String message, LocalDateTime now, User author, Status status, String course) {
        this.title = title;
        this.message = message;
        this.creationDate = now;
        this.author = author;
        this.status = status;
        this.course = course;
    }
}
