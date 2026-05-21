package com.carticare.carticare.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "chat_history")
public class ChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 여러 대화를 한유저가 받음
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String role; // 역할 (user = 사용자 / assistant = AI)
    @Column(columnDefinition = "TEXT")
    private String message; // 대화내용

    private boolean deleted = false;  // 삭제 여부 (기본값 false)
    private LocalDateTime deletedAt;  // 삭제일

    private LocalDateTime createdAt;


    @Builder
    public ChatHistory(User user, String role, String message) {
        this.user = user;
        this.role = role;
        this.message = message;
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
    }

    // 소프트 딜리트 메서드
    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
