package com.carticare.carticare.service;

import com.carticare.carticare.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.carticare.carticare.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 저장(회원가입)
    public User save(User user) {
        return userRepository.save(user);
    }

    // 유저 전체 조회
    public List<User> findAll() {

        return userRepository.findAll().stream()
                .filter(u -> !u.isDeleted()) // 삭제안된것만 반환
                .collect(Collectors.toList());
    }

    // 유저 단건 조회
    public User findById(Long id) {
        User user =  userRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("해당 유저를 찾을수 없습니다"));
            // 삭제된 유저면 예외 처리
        if (user.isDeleted()) {
            throw new RuntimeException("삭제된 유저입니다");
        }
        return user;
    }

    // 유저 삭제 (소프트 딜리트)
    public void delete(Long id) {
        User user = findById(id);  // 유저 조회
        user.delete();             // deleted = true 로 변경
        userRepository.save(user); // 변경사항 저장
    }


}
