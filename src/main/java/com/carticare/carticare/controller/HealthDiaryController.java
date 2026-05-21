package com.carticare.carticare.controller;

import com.carticare.carticare.dto.requestdto.HealthDiaryRequestDto;
import com.carticare.carticare.dto.responsedto.HealthDiaryResponseDto;
import com.carticare.carticare.entity.HealthDiary;
import com.carticare.carticare.entity.User;
import com.carticare.carticare.service.HealthDiaryService;
import com.carticare.carticare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-diary")
public class HealthDiaryController {

    private final HealthDiaryService healthDiaryService;
    private final UserService userService;

    // 관련 일지 저장
    // Post/ api/health-diary
    @PostMapping
    public ResponseEntity<HealthDiaryResponseDto> save(@RequestBody HealthDiaryRequestDto requestDto) {
        // User id로 유저 정보 저장
        User user = userService.findById(requestDto.getUserId());
        // 조회후 저장
        HealthDiary healthDiary = healthDiaryService.save(requestDto.toEntity(user));
       return ResponseEntity.ok(new HealthDiaryResponseDto(healthDiary));
    }

    // 전체조회
    // Get/ api/health-diary
    @GetMapping
    public ResponseEntity<List<HealthDiaryResponseDto>> findAll() {
        return ResponseEntity.ok(
                healthDiaryService.findAll().stream()
                        .map(HealthDiaryResponseDto:: new)
                        .collect(Collectors.toList())
        );
    }

    // 단건조회
    // Get/ api-health-diart/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HealthDiaryResponseDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(new HealthDiaryResponseDto(healthDiaryService.findById(id)));
    }

    // 삭제
    // DELETE/ api/health-diary/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        healthDiaryService.delete(id);
        return ResponseEntity.ok("삭제완료");
    }
}
