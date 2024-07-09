package com.example.school.service.impl;

import com.example.school.domain.Grade;
import com.example.school.domain.Student;
import com.example.school.repository.StudentRepository;
import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public double calculateGPA(Long studentId) {
        Student student = getStudentById(studentId);
        if (student == null || student.getGrades().isEmpty()) {
            return 0.0;
        }
        return student.getGrades().stream().mapToInt(Grade::getScore).average().orElse(0.0);
    }
}