package com.carticare.carticare.controller;

import com.carticare.carticare.dto.requestdto.HealthInputRequestDto;
import com.carticare.carticare.dto.responsedto.HealthInputResponseDto;
import com.carticare.carticare.entity.HealthInput;
import com.carticare.carticare.entity.User;
import com.carticare.carticare.service.HealthInputService;
import com.carticare.carticare.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-input")
public class HealthInputController {

    private final HealthInputService healthInputService;
    private final UserService userService;


    // 건강 정보 저장
    // POST /api/health-input
    @PostMapping
    public ResponseEntity<HealthInputResponseDto> save(@RequestBody HealthInputRequestDto requestDto) {
        // 유저 id로 user를 조회한다
        User user = userService.findById(requestDto.getUserId());
        // 조회 후 저장
        HealthInput healthInput = healthInputService.save(requestDto.toEntity(user));
        return ResponseEntity.ok(new HealthInputResponseDto(healthInput));
    }

    // 전체 조회
    // Get/api/health-input
    @GetMapping
    public ResponseEntity<List<HealthInputResponseDto>> findAll() {
        return ResponseEntity.ok(
                healthInputService.findAll().stream()
                        .map(HealthInputResponseDto::new)
                        .collect(Collectors.toList())
        );
    }

    // 단건 조회
    // Get/ api/health-input/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HealthInputResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new HealthInputResponseDto(healthInputService.findById(id)));
    }

    // 삭제 (소프트 딜리트)
    // DELETE /api/health-input/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        healthInputService.delete(id);
        return ResponseEntity.ok("삭제완료");
    }

}
