package com.carticare.carticare.dto.responsedto;

import com.carticare.carticare.entity.ChatHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ChatHistoryResponseDto {

    private Long id;        // 고유 번호
    private Long userId;    // 유저 고유 번호
    private String role;    // 역할 (user / assistant)
    private String message; // 대화 내용

    //Entity -> dto 변환
    public ChatHistoryResponseDto(ChatHistory chatHistory) {
        this.id = chatHistory.getId();
        this.userId = chatHistory.getUser().getId();
        this.role = chatHistory.getRole();
        this.message = chatHistory.getMessage();
    }

}
