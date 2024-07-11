package com.example.school.service.impl;

import com.example.school.dto.GradeDTO;
import com.example.school.mapper.GradeMapper;
import com.example.school.repository.GradeRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final GradeMapper gradeMapper;

    @Override
    @Transactional
    public GradeDTO saveGrade(GradeDTO gradeDTO) {
        final var student = studentRepository.findById(gradeDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        final var grade = gradeMapper.toEntity(gradeDTO, student);
        final var savedGrade = gradeRepository.save(grade);
        return gradeMapper.toDto(savedGrade);
    }

    @Override
    public Page<GradeDTO> getAllGrades(int page, int size) {
        final var pageable = PageRequest.of(page, size);
        return gradeRepository.findAll(pageable).map(gradeMapper::toDto);
    }

    @Override
    public GradeDTO getGradeById(long id) {
        return gradeRepository.findById(id)
                .map(gradeMapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public GradeDTO updateGrade(long id, GradeDTO gradeDTO) {
        final var grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        grade.setSubject(gradeDTO.getSubject());
        grade.setScore(gradeDTO.getScore());
        final var updatedGrade = gradeRepository.save(grade);
        return gradeMapper.toDto(updatedGrade);
    }

    @Override
    @Transactional
    public void deleteGrade(long id) {
        gradeRepository.deleteById(id);
    }
}
