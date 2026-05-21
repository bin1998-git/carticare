package com.carticare.carticare.dto.requestdto;

import com.carticare.carticare.entity.HealthInput;
import com.carticare.carticare.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthInputRequestDto {
    private Long userId; // 유저 고유 번호
    private String exerciseFreq; // 운동 빈도
    private String jobType; // 유저 직업 유형
    private String painArea; // 통증 부위
    private int painLevel; // 통증 레벨(1 ~ 10)
    private boolean familyHistory; // 가족력 여부

    @Builder
    // Entitiy 변환 메서드
    public HealthInput toEntity(User user) {
        return HealthInput.builder()
                .user(user)
                .exerciseFreq(exerciseFreq)
                .jobType(jobType)
                .painArea(painArea)
                .painLevel(painLevel)
                .familyHistory(familyHistory)
                .build();
    }

}
