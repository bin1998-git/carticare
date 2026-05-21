package com.carticare.carticare.controller;

import com.carticare.carticare.dto.requestdto.HealthResultRequestDto;
import com.carticare.carticare.dto.responsedto.HealthResultResponseDto;
import com.carticare.carticare.entity.HealthResult;
import com.carticare.carticare.entity.User;
import com.carticare.carticare.service.HealthResultService;
import com.carticare.carticare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-result")


public class HealthResultController {


    private final HealthResultService healthResultService;
    private final UserService userService;

    // 분석 결과 저장
    // POST /api/health-result
    @PostMapping
    public ResponseEntity<HealthResultResponseDto> save(@RequestBody HealthResultRequestDto requestDto) {
        // user id로 user정보를 조회
        User user = userService.findById(requestDto.getUserId());
        // 조회후 저장
        HealthResult healthResult = healthResultService.save(requestDto.toEntity(user));
        return ResponseEntity.ok(new HealthResultResponseDto(healthResult));
    }

    // 전체 조회
    // GET /api/health-result
    @GetMapping
    public ResponseEntity<List<HealthResultResponseDto>> findAll() {
        return ResponseEntity.ok(
                healthResultService.findAll().stream()
                        .map(HealthResultResponseDto::new)
                        .collect(Collectors.toList())
        );
    }

    // 단건 조회
    // Get/ api/health-result/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HealthResultResponseDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(new HealthResultResponseDto(healthResultService.findById(id)));
    }

    // 삭제 (소프트 딜리트)
    // DELETE /api/health-result/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        healthResultService.delete(id);
        return ResponseEntity.ok("삭제완료");
    }
}
