package com.example.school.service.impl;

import com.example.school.domain.Grade;
import com.example.school.domain.Student;
import com.example.school.dto.StudentDTO;
import com.example.school.mapper.StudentMapper;
import com.example.school.repository.StudentRepository;
import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElse(null);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public double calculateGPA(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null || student.getGrades().isEmpty()) {
            return 0.0;
        }
        return student.getGrades().stream().mapToInt(Grade::getScore).average().orElse(0.0);
    }
}