package com.carticare.carticare.dto.responsedto;

import com.carticare.carticare.entity.HealthDiary;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthDiaryResponseDto {
    private Long id;              // 고유 번호
    private Long userId;          // 유저 고유 번호
    private int painLevel;        // 오늘 통증 정도
    private boolean exerciseDone; // 오늘 운동 했는지
    private String memo;          // 오늘 메모


    // Entity -> dto
    public HealthDiaryResponseDto(HealthDiary healthDiary) {
        this.id = healthDiary.getId();
        this.userId = healthDiary.getUser().getId();
        this.painLevel = healthDiary.getPainLevel();
        this.exerciseDone = healthDiary.isExerciseDone();
        this.memo = healthDiary.getMemo();

    }
}
