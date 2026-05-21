package com.carticare.carticare.dto.requestdto;

import com.carticare.carticare.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private String name; // 이름
    private int age; // 나이
    private double weight; // 몸무게
    private double height; // 키

    @Builder
    // Entity로 변환하는 메서드
    public User toEntity() {
        return User.builder()
                .name(name)
                .age(age)
                .weight(weight)
                .height(height)
                .build();

    }
}
