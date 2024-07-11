package com.example.school.service;

import com.example.school.dto.StudentDTO;
import org.springframework.data.domain.Page;

public interface StudentService {
    StudentDTO saveStudent(StudentDTO studentDTO);

    Page<StudentDTO> getAllStudents(int page, int size);

    StudentDTO getStudentById(long id);

    void deleteStudent(long id);

    double calculateGPA(long studentId);

    Page<StudentDTO> searchStudents(String keyword, int page, int size);

    StudentDTO updateStudent(long id, StudentDTO studentDTO);
}
