package com.example.school.service.impl;

import com.example.school.domain.Grade;
import com.example.school.dto.GradeDTO;
import com.example.school.mapper.GradeMapper;
import com.example.school.repository.GradeRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    private final GradeMapper gradeMapper;

    @Override
    public GradeDTO saveGrade(GradeDTO gradeDTO) {
        Grade grade = gradeMapper.toEntity(gradeDTO);
        Grade savedGrade = gradeRepository.save(grade);
        return gradeMapper.toDto(savedGrade);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream()
                .map(gradeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        return gradeRepository.findById(id)
                .map(gradeMapper::toDto)
                .orElse(null);
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        grade.setSubject(gradeDTO.getSubject());
        grade.setScore(gradeDTO.getScore());
        Grade updatedGrade = gradeRepository.save(grade);
        return gradeMapper.toDto(updatedGrade);
    }

    @Override
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
