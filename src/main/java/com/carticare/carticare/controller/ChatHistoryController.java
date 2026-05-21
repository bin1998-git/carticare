package com.carticare.carticare.controller;

import com.carticare.carticare.dto.requestdto.ChatHistoryRequestDto;
import com.carticare.carticare.dto.responsedto.ChatHistoryResponseDto;
import com.carticare.carticare.entity.ChatHistory;
import com.carticare.carticare.entity.User;
import com.carticare.carticare.service.ChatHistoryService;
import com.carticare.carticare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-history")
public class ChatHistoryController {

    private final ChatHistoryService chatHistoryService;
    private final UserService userService;

    // 저장
    // Post/ api/chat-history
    @PostMapping
    public ResponseEntity<ChatHistoryResponseDto> save(@RequestBody ChatHistoryRequestDto requestDto) {
        // UserId로 유저조회
        User user = userService.findById(requestDto.getUserId());
        // 조회 후저장하는 시스템
        ChatHistory chatHistory = chatHistoryService.save(requestDto.toEntity(user));
        return ResponseEntity.ok(new ChatHistoryResponseDto(chatHistory));
    }

    // 전체조회
    // Get/ api/chat-history
    @GetMapping
    public ResponseEntity<List<ChatHistoryResponseDto>> findAll() {
        return ResponseEntity.ok(
                chatHistoryService.findAll().stream()
                        .map(ChatHistoryResponseDto::new)
                        .collect(Collectors.toList())
        );


    }

    // 단건 조회
    // GET /api/chat-history/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ChatHistoryResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ChatHistoryResponseDto(chatHistoryService.findById(id)));
    }

    // 삭제
    // DELETE /api/chat-history/{id}
    @DeleteMapping
    public ResponseEntity<String> delete(@PathVariable long id) {
        chatHistoryService.delete(id);
        return ResponseEntity.ok("삭제 완료");
    }


}
