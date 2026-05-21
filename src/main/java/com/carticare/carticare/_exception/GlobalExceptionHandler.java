package com.carticare.carticare._exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든 Controller에서 발생하는 예외를 잡아줌
public class GlobalExceptionHandler {

    // RuntimeException 처리
    // 예: "해당 유저가 없습니다", "삭제된 유저입니다" 등
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400 에러
                .body(e.getMessage());          // 에러 메시지 반환
    }

    // NullPointerException 처리
    // 예: null 값 접근할 때
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500 에러
                .body("서버 오류가 발생했습니다");
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500 에러
                .body("알 수 없는 오류가 발생했습니다");
    }
}