package com.example.school.service;

import com.example.school.domain.Grade;

import java.util.List;

public interface GradeService {
    Grade saveGrade(Grade grade);

    List<Grade> getAllGrades();

    Grade getGradeById(Long id);

    Grade updateGrade(Long id, Grade gradeDetails);

    void deleteGrade(Long id);
}
