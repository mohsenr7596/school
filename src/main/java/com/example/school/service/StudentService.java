package com.example.school.service;

import com.example.school.domain.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void deleteStudent(Long id);

    double calculateGPA(Long studentId);
}
