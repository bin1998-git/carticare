package com.carticare.carticare.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exercise_checklist")

public class ExerciseChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 체크리스트를 한유저가 받음
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String exerciseName; // 운동명
    private boolean isDone; // 완료 여부

    private boolean deleted = false;  // 삭제 여부 (기본값 false)
    private LocalDateTime deletedAt;  // 삭제일

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    @Builder
    public ExerciseChecklist(User user, String exerciseName) {
        this.user = user;
        this.exerciseName = exerciseName;
        this.isDone = false;          // 처음엔 무조건 미완료
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 완료 여부 토글 메서드
    // isDone이 false면 true로, true면 false로 변경
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    // 소프트 딜리트 메서드
    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
