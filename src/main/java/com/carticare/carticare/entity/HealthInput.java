package com.carticare.carticare.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "health_input")
public class HealthInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 건강데이터를 1명 유저가 받게 (N:1)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String exerciseFreq; // 운동빈도
    private String jobType; // 직업
    private String painArea; // 통증 부위
    private int painLevel; // 통증 레벨(1~10)
    private boolean familyHistory; // 가족력 true,false

    private boolean deleted = false;  // 삭제 여부 (기본값 false)
    private LocalDateTime deletedAt;  // 삭제일

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public HealthInput(User user, String exerciseFreq, String jobType,
                       String painArea, int painLevel, boolean familyHistory) {
        this.user = user;
        this.exerciseFreq = exerciseFreq;
        this.jobType = jobType;
        this.painArea = painArea;
        this.painLevel = painLevel;
        this.familyHistory = familyHistory;
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
