package com.example.school.mapper;

import com.example.school.domain.Student;
import com.example.school.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
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
        return student;
    }
}