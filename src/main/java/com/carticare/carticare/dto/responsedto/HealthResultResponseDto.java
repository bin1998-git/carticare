package com.carticare.carticare.dto.responsedto;

import com.carticare.carticare.entity.HealthResult;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthResultResponseDto {
    private Long id;                  // 고유 번호
    private Long userId;              // 유저 고유 번호
    private String riskLevel;         // 위험도
    private String recommendation;    // 전체 관리 방법 추천
    private String exerciseRecommend; // 운동 추천 내용
    private String dietRecommend;     // 식단 추천 내용


    // Entity -> DTO 변환
    public HealthResultResponseDto(HealthResult healthResult) {
        this.id = healthResult.getId();
        this.userId = healthResult.getUser().getId();
        this.riskLevel = healthResult.getRiskLevel();
        this.recommendation = healthResult.getRecommendation();
        this.exerciseRecommend = healthResult.getExerciseRecommend();
        this.dietRecommend = healthResult.getDietRecommend();
    }
}
