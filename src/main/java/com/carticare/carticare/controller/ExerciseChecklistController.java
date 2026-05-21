package com.carticare.carticare.controller;

import com.carticare.carticare.dto.requestdto.ExerciseChecklistRequestDto;
import com.carticare.carticare.dto.responsedto.ExerciseChecklistResponseDto;
import com.carticare.carticare.entity.ExerciseChecklist;
import com.carticare.carticare.entity.User;
import com.carticare.carticare.service.ExerciseChecklistService;
import com.carticare.carticare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exercise-checklist")
public class ExerciseChecklistController {
    private final UserService userService;
    private final ExerciseChecklistService exerciseChecklistService;

    // 운동 체크리스트 저장
    // post/ api/exercise-checklist
    @PostMapping
    public ResponseEntity<ExerciseChecklistResponseDto> save(@RequestBody ExerciseChecklistRequestDto requestDto) {
        // userid로 유저정보조회
        User user = userService.findById(requestDto.getUserId());
        // 정보조회후 저장
        ExerciseChecklist exerciseChecklist = exerciseChecklistService.save(requestDto.toEntity(user));
        return ResponseEntity.ok(new ExerciseChecklistResponseDto(exerciseChecklist));
    }

    // 전체 조회
    // GET /api/exercise-checklist
    @GetMapping
    public ResponseEntity<List<ExerciseChecklistResponseDto>> findAll() {
        return ResponseEntity.ok(
                exerciseChecklistService.findAll().stream()
                        .map(ExerciseChecklistResponseDto::new)
                        .collect(Collectors.toList())
        );
    }

    // 단건 조회 id로 조회
    // Get/api/exercise-checklist/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseChecklistResponseDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(new ExerciseChecklistResponseDto(exerciseChecklistService.findById(id)));
    }

    // 완료 여부 토글 (체크/언체크)
    // PATCH /api/exercise-checklist/{id}/toggle
    @PatchMapping("/{id}")
    public ResponseEntity<ExerciseChecklistResponseDto> toggleDone(@PathVariable long id) {
        return ResponseEntity.ok(new ExerciseChecklistResponseDto(exerciseChecklistService.toggleDone(id)));
    }

    // 삭제
    // delete/api/exercise-checklist/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        exerciseChecklistService.delete(id);
        return ResponseEntity.ok("삭제완료");
    }
}
