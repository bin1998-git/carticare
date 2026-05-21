package com.carticare.carticare.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "health_diary")
public class HealthDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 여러 일지를 1명유저가 받음
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int painLevel; // 통증의 고통 크기( 1~ 10)
    private boolean exerciseDone; // 당일 운동 체크

    @Column(columnDefinition = "TEXT") // 긴 문자대비
    private String memo;

    private boolean deleted = false;  // 삭제 여부 (기본값 false)
    private LocalDateTime deletedAt;  // 삭제일

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public HealthDiary(User user, int painLevel,
                       boolean exerciseDone, String memo) {
        this.user = user;
        this.painLevel = painLevel;
        this.exerciseDone = exerciseDone;
        this.memo = memo;
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 소프트 딜리트 메서드
    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
