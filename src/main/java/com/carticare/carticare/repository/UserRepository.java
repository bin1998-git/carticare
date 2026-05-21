package com.carticare.carticare.repository;

import com.carticare.carticare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티, PK타입>
// 기본 CRUD 자동 제공 (save, findById, findAll, delete 등)

public interface UserRepository extends JpaRepository<User, Long> {
}
