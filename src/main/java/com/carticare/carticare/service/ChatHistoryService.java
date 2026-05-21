package com.carticare.carticare.service;

import com.carticare.carticare.entity.ChatHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.carticare.carticare.repository.ChatHistoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

    private final ChatHistoryRepository chatHistoryRepository;

    // 대화 내용 저장
    public ChatHistory save(ChatHistory chatHistory) {
        return chatHistoryRepository.save(chatHistory);
    }

    // 대화 내용 전체 조회
    public List<ChatHistory> findAll() {

        return chatHistoryRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .collect(Collectors.toList());
    }

    // 대화 내용 단건 조회
    public ChatHistory findById(Long id) {
        ChatHistory chatHistory = chatHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 대화 내용이 없습니다"));
        if (chatHistory.isDeleted()) {
            throw new RuntimeException("삭제된 대화 내용 입니다");

        }
        return chatHistory;
    }

    // 삭제 (소프트 딜리트)
    public void delete(Long id) {
        ChatHistory chatHistory = findById(id);
        chatHistory.delete();                      // deleted = true
        chatHistoryRepository.save(chatHistory);   // 변경사항 저장
    }
}
