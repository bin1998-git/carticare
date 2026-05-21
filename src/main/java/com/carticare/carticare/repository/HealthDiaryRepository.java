package com.carticare.carticare.repository;

import com.carticare.carticare.entity.HealthDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthDiaryRepository extends JpaRepository<HealthDiary, Long> {
}
