package com.carticare.carticare.service;

import com.carticare.carticare.entity.HealthResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.carticare.carticare.repository.HealthResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HealthResultService {
    private final HealthResultRepository healthResultRepository;

    // 분석 결과 저장
    public HealthResult save(HealthResult healthResult) {
        return healthResultRepository.save(healthResult);
    }


    // 전체 조회 (삭제된 것 제외)
    public List<HealthResult> findAll() {
        return healthResultRepository.findAll().stream()
                .filter(h -> !h.isDeleted())
                .collect(Collectors.toList());
    }


    // 단건 조회 (삭제된 것 제외)
    public HealthResult findById(Long id) {
        HealthResult healthResult = healthResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 분석 결과가 없습니다"));
        if (healthResult.isDeleted()) {
            throw new RuntimeException("삭제된 분석 결과입니다");
        }
        return healthResult;
    }

    // 삭제 (소프트 딜리트)
    public void delete(Long id) {
        HealthResult healthResult = findById(id);
        healthResult.delete();                    // deleted = true
        healthResultRepository.save(healthResult); // 변경사항 저장
    }

}
