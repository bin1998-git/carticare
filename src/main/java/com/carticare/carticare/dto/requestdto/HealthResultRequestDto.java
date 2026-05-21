package com.carticare.carticare.dto.requestdto;

import com.carticare.carticare.entity.HealthResult;
import com.carticare.carticare.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthResultRequestDto {
    private Long userId;           // 유저 고유 번호
    private String riskLevel;      // 위험도 (낮음/중간/높음)
    private String recommendation; // 전체 관리 방법 추천
    private String exerciseRecommend; // 운동 추천 내용
    private String dietRecommend;  // 식단 추천 내용

    @Builder
    // Entity로 변환하는 메서드
    public HealthResult toEntity(User user) {
        return HealthResult.builder()
                .user(user)
                .riskLevel(riskLevel)
                .recommendation(recommendation)
                .exerciseRecommend(exerciseRecommend)
                .dietRecommend(dietRecommend)
                .build();
    }
}
