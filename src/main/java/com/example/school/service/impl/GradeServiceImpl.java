package com.example.school.service.impl;

import com.example.school.domain.Grade;
import com.example.school.domain.Student;
import com.example.school.repository.GradeRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Override public Grade saveGrade(Grade grade) {
        // Ensure the student exists
        Student student = studentRepository.findById(grade.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        grade.setStudent(student);
        return gradeRepository.save(grade);
    }

    @Override public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    @Override public Grade updateGrade(Long id, Grade gradeDetails) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        grade.setSubject(gradeDetails.getSubject());
        grade.setScore(gradeDetails.getScore());
        return gradeRepository.save(grade);
    }

    @Override public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
