package com.carticare.carticare.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user") // user테이블과 매핑
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private double weight;
    private double height;

    private boolean deleted = false;   // 삭제 여부 (기본값 false)
    private LocalDateTime deletedAt;   // 삭제일

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // User 1명이 여러 건강입력 데이터 가짐 (1:n)관계
    @OneToMany(mappedBy = "user")
    private List<HealthInput> healthInputs = new ArrayList<>();
    // User 1명이 여러 분석 결과 가짐 (1:n)관계
    @OneToMany(mappedBy = "user")
    private List<HealthResult> healthResults = new ArrayList<>();
    // User 1명이 여러 관리 데이터 가짐 (1:n)관계
    @OneToMany(mappedBy = "user")
    private List<HealthDiary> healthDiaries = new ArrayList<>();
    // User 1명이 챗봇 대화 데이터 가짐 (1:n)관계
    @OneToMany(mappedBy = "user")
    private List<ChatHistory> chatHistories = new ArrayList<>();
    // User 1명이 운동 체크리스트 데이터 가짐 (1:n)관계
    @OneToMany(mappedBy = "user")
    private List<ExerciseChecklist> exerciseChecklists = new ArrayList<>();


    // Builder 패턴 생성자
    // @NoArgsConstructor 와 같이 쓰려면 @Builder는 전체 생성자에 붙여야 함
    @Builder
    public User(String name, int age, double weight, double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;


        this.createdAt = LocalDateTime.now(); // 생성일 자동 설정
        this.updatedAt = LocalDateTime.now(); // 수정일 자동 설정


    }
    // 소프트 딜리트 메서드
    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }

}
