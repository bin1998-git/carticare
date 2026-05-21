package com.carticare.carticare.dto.requestdto;

import com.carticare.carticare.entity.ExerciseChecklist;
import com.carticare.carticare.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseChecklistRequestDto {

    private Long userId; // 유저 고유 번호
    private String exerciseName; // 운동 이름

    @Builder
    // Entity 변환 메서드
    public ExerciseChecklist toEntity(User user) {
        return ExerciseChecklist.builder()
                .user(user)
                .exerciseName(exerciseName)
                .build();
    }

}
