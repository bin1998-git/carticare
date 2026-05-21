package com.carticare.carticare.dto.responsedto;

import com.carticare.carticare.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
    private Long id; // 유저 고유 번호
    private String name; // 이름
    private int age; // 나이
    private double weight; // 몸무게
    private double height;// 키



    // Entity -> DTO 변환
    // service에서 사용할거임
    public UserResponseDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.age = user.getAge();
    this.weight = user.getWeight();
    this.height = user.getHeight();
    }

}
