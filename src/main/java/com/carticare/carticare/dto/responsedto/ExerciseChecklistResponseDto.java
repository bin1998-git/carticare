package com.carticare.carticare.dto.responsedto;

import com.carticare.carticare.entity.ExerciseChecklist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseChecklistResponseDto {
    private Long id;             // 고유 번호
    private Long userId;         // 유저 고유 번호
    private String exerciseName; // 운동 이름
    private boolean isDone;      // 완료 여부 (true/false)

    // Entity -> dto 변환


    public ExerciseChecklistResponseDto(ExerciseChecklist exerciseChecklist) {
        this.id = exerciseChecklist.getId();
        this.userId = exerciseChecklist.getUser().getId();
        this.exerciseName = exerciseChecklist.getExerciseName();
        this.isDone = exerciseChecklist.isDone();
    }
}
