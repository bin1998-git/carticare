package com.carticare.carticare.dto.responsedto;

import com.carticare.carticare.entity.HealthInput;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthInputResponseDto {
    private Long id;               // 고유 번호
    private Long userId;           // 유저 고유 번호
    private String exerciseFreq;   // 운동 빈도
    private String jobType;        // 직업 유형
    private String painArea;       // 통증 부위
    private int painLevel;         // 통증 정도
    private boolean familyHistory; // 가족력 여부


    // Entity -> Dto
    public HealthInputResponseDto(HealthInput healthInput) {
        this.id = healthInput.getId();
        this.userId = healthInput.getUser().getId();
        this.exerciseFreq = healthInput.getExerciseFreq();
        this.jobType = healthInput.getJobType();
        this.painArea = healthInput.getPainArea();
        this.painLevel = healthInput.getPainLevel();
        this.familyHistory = healthInput.isFamilyHistory();
    }
}
