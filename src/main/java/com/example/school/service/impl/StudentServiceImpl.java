package com.example.school.service.impl;

import com.example.school.domain.Grade;
import com.example.school.dto.StudentDTO;
import com.example.school.mapper.StudentMapper;
import com.example.school.repository.StudentRepository;
import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        final var student = studentMapper.toEntity(studentDTO);
        final var savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public Page<StudentDTO> getAllStudents(int page, int size) {
        final var pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable).map(studentMapper::toDto);
    }

    @Override
    public StudentDTO getStudentById(long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public double calculateGPA(long studentId) {
        final var student = studentRepository.findById(studentId).orElse(null);
        if (student == null || student.getGrades().isEmpty()) {
            return Double.NaN;
        }
        return student.getGrades().stream().mapToInt(Grade::getScore).average().orElse(0.0);
    }

    @Override
    public Page<StudentDTO> searchStudents(String keyword, int page, int size) {
        final var pageable = PageRequest.of(page, size);
        return studentRepository.searchByKeyword(keyword, pageable)
                .map(studentMapper::toDto);
    }

    @Override
    public StudentDTO updateStudent(long id, StudentDTO studentDTO) {
        final var student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());

        final var updatedStudent = studentRepository.save(student);
        return studentMapper.toDto(updatedStudent);
    }
}