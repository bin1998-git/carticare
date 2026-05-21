package com.carticare.carticare.controller;

import com.carticare.carticare.dto.requestdto.UserRequestDto;
import com.carticare.carticare.dto.responsedto.UserResponseDto;
import com.carticare.carticare.entity.User;
import com.carticare.carticare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // json형태 데이터 변환
@RequiredArgsConstructor // final 필드 생성자 자동 생성
@RequestMapping("/api/user") // 기본 url 경로 설정
public class UserController {

    private final UserService userService;

    // 유저 등록 (회원가입)
    // post/ api/user
    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestDto) {
        User user = userService.save(requestDto.toEntity());
        return ResponseEntity.ok(new UserResponseDto(user));
    }

    // 전체 유저 조회
    // get
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(
                userService.findAll().stream()
                        .map(UserResponseDto::new)
                        .collect(Collectors.toList())
        );
    }

    // {id}로 유저 단거 조회

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserResponseDto(userService.findById(id)));
    }

    // {id}로유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("삭제 완료");
    }
}
