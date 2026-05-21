package com.carticare.carticare.service;

import com.carticare.carticare.entity.ExerciseChecklist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.carticare.carticare.repository.ExerciseChecklistRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseChecklistService {

    private final ExerciseChecklistRepository exerciseChecklistRepository;

    // 운동 체크리스트 저장
    public ExerciseChecklist save(ExerciseChecklist exerciseChecklist) {
        return exerciseChecklistRepository.save(exerciseChecklist);
    }

    // 운동 체크리스트 전체 조회(삭제 된것 제외)
    public List<ExerciseChecklist> findAll() {

        return exerciseChecklistRepository.findAll().stream()
                .filter(e -> !e.isDeleted())
                .collect(Collectors.toList());
    }

    // 운동 체크리스트 단건 조회(삭제된거 제외)
    public ExerciseChecklist findById(Long id) {
        ExerciseChecklist checklist = exerciseChecklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 운동체크리스트가 없습니다"));
        if (checklist.isDeleted()) {
            throw new RuntimeException("삭제된 운동 체크리스트 입니다");
        }
        return checklist;
    }

    // 운동 완료 여부 변경
    public ExerciseChecklist toggleDone(Long id) {

        // id로 체크리스트 조회
        ExerciseChecklist checklist = findById(id);

        // isDone 변경
        checklist.toggleDone();

        // 변경된 내용 저장하고 반환
        return exerciseChecklistRepository.save(checklist);
    }

    // 삭제 (소프트 딜리트)
    public void delete(Long id) {
        ExerciseChecklist checklist = findById(id);
        checklist.delete();                              // deleted = true
        exerciseChecklistRepository.save(checklist);    // 변경사항 저장
    }

}
