package com.example.school.mapper;

import com.example.school.domain.Grade;
import com.example.school.domain.Student;
import com.example.school.dto.GradeDTO;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public GradeDTO toDto(Grade grade) {
        if (grade == null) {
            return null;
        }
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(grade.getId());
        gradeDTO.setSubject(grade.getSubject());
        gradeDTO.setScore(grade.getScore());
        gradeDTO.setStudentId(grade.getStudent().getId());
        return gradeDTO;
    }

    public Grade toEntity(GradeDTO gradeDTO, Student student) {
        if (gradeDTO == null) {
            return null;
        }
        Grade grade = new Grade();
        grade.setId(gradeDTO.getId());
        grade.setSubject(gradeDTO.getSubject());
        grade.setScore(gradeDTO.getScore());
        grade.setStudent(student);
        return grade;
    }
}
