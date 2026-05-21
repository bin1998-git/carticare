package com.carticare.carticare.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "health_result")
public class HealthResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String riskLevel;

    @Column(columnDefinition = "TEXT") //긴 문자열 저장을 위해 TEXT 타입 지정
    private String recommendation; // 총 관리 방법 추천글
    @Column(columnDefinition = "TEXT")
    private String exerciseRecommend; // 운동 추천 내용글
    @Column(columnDefinition = "TEXT")
    private String dietRecommend; // 다이어트 식단 추천내용글

    private boolean deleted = false;   // 삭제 여부 (기본값 false)
    private LocalDateTime deletedAt;   // 삭제일


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public HealthResult(User user, String riskLevel, String recommendation,
                        String exerciseRecommend, String dietRecommend) {
        this.user = user;
        this.riskLevel = riskLevel;
        this.recommendation = recommendation;
        this.exerciseRecommend = exerciseRecommend;
        this.dietRecommend = dietRecommend;
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
