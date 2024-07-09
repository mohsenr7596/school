package com.example.school.service;

import com.example.school.domain.Grade;
import com.example.school.dto.GradeDTO;

import java.util.List;

public interface GradeService {

    GradeDTO saveGrade(GradeDTO gradeDTO);

    List<GradeDTO> getAllGrades();

    GradeDTO getGradeById(Long id);

    GradeDTO updateGrade(Long id, GradeDTO gradeDTO);

    void deleteGrade(Long id);
}
