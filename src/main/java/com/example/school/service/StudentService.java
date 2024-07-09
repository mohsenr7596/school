package com.example.school.service;

import com.example.school.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    void deleteStudent(Long id);

    double calculateGPA(Long studentId);
}
