package com.carticare.carticare.service;

import com.carticare.carticare.entity.HealthInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.carticare.carticare.repository.HealthInputRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HealthInputService {

    private final HealthInputRepository healthInputRepository;

    // 건강 정보 저장
    public HealthInput save(HealthInput healthInput) {
        return healthInputRepository.save(healthInput);
    }


    // 전체 조회 (삭제된 것 제외)
    public List<HealthInput> findAll() {
        return healthInputRepository.findAll().stream()
                .filter(h -> !h.isDeleted())
                .collect(Collectors.toList());
    }

    // 단건 조회 (삭제된 것 제외)
    public HealthInput findById(Long id) {
        HealthInput healthInput = healthInputRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 건강 정보가 없습니다"));
        if (healthInput.isDeleted()) {
            throw new RuntimeException("삭제된 건강 정보입니다");
        }
        return healthInput;
    }


    // 건강 정보 삭제 (소프트 딜리트)
    public void delete(Long id) {
        HealthInput healthInput = findById(id);
        healthInput.delete();                     // deleted = true
        healthInputRepository.save(healthInput);  // 변경사항 저장
    }
}
