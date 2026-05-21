package com.carticare.carticare.dto.requestdto;

import com.carticare.carticare.entity.HealthDiary;
import com.carticare.carticare.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthDiaryRequestDto {
    private Long userId;          // 유저 고유 번호
    private int painLevel;        // 오늘 통증 정도 (1~10)
    private boolean exerciseDone; // 오늘 운동 했는지 (true/false)
    private String memo;          // 오늘 메모


    @Builder
    // Entity로 변환하는 메서드
    public HealthDiary toEntity(User user) {
        return HealthDiary.builder()
                .user(user)
                .painLevel(painLevel)
                .exerciseDone(exerciseDone)
                .memo(memo)
                .build();
    }
}
