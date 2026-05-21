package com.carticare.carticare.dto.requestdto;

import com.carticare.carticare.entity.ChatHistory;
import com.carticare.carticare.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatHistoryRequestDto {

    private Long userId;   // 유저 고유 번호
    private String role;   // 역할 (user / assistant)
    private String message; // 대화 내용

    @Builder
    // Entity 변환
    public ChatHistory toEntity(User user) {
        return ChatHistory.builder()
                .user(user)
                .role(role)
                .message(message)
                .build();
    }
}
