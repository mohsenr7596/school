package com.example.school.mapper;

import com.example.school.domain.Student;
import com.example.school.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {
    private final GradeMapper gradeMapper;

    public StudentDTO toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setGrades(student.getGrades().stream()
                .map(gradeMapper::toDto)
                .collect(Collectors.toList()));
        return studentDTO;
    }

    public Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null;
        }
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setGrades(studentDTO.getGrades().stream()
                .map(gradeMapper::toEntity)
                .collect(Collectors.toList()));
        return student;
    }
}