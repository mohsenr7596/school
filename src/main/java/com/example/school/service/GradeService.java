package com.example.school.service;

import com.example.school.dto.GradeDTO;
import org.springframework.data.domain.Page;

public interface GradeService {

    GradeDTO saveGrade(GradeDTO gradeDTO);

    Page<GradeDTO> getAllGrades(int page, int size);

    GradeDTO getGradeById(long id);

    GradeDTO updateGrade(long id, GradeDTO gradeDTO);

    void deleteGrade(long id);
}
