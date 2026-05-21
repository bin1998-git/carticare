package com.carticare.carticare.service;


import com.carticare.carticare.entity.HealthDiary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.carticare.carticare.repository.HealthDiaryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HealthDiaryService {

    private final HealthDiaryRepository healthDiaryRepository;

    // 관리 일지 저장
    public HealthDiary save(HealthDiary healthDiary) {
        return healthDiaryRepository.save(healthDiary);
    }


    // 전체 조회 (삭제된 것 제외)
    public List<HealthDiary> findAll() {
        return healthDiaryRepository.findAll().stream()
                .filter(h -> !h.isDeleted())
                .collect(Collectors.toList());
    }


    // 단건 조회 (삭제된 것 제외)
    public HealthDiary findById(Long id) {
        HealthDiary healthDiary = healthDiaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 관리 일지가 없습니다"));
        if (healthDiary.isDeleted()) {
            throw new RuntimeException("삭제된 관리 일지입니다");
        }
        return healthDiary;
    }


    // 삭제 (소프트 딜리트)
    public void delete(Long id) {
        HealthDiary healthDiary = findById(id);
        healthDiary.delete();                    // deleted = true
        healthDiaryRepository.save(healthDiary); // 변경사항 저장
    }
}
