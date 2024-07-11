package com.example.school.service;

import com.example.school.domain.Grade;
import com.example.school.domain.Student;
import com.example.school.dto.StudentDTO;
import com.example.school.mapper.StudentMapper;
import com.example.school.repository.StudentRepository;
import com.example.school.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Ali Alavi");
        studentDTO.setEmail("ali.alavi@example.com");

        Student student = new Student();
        student.setName("Ali Alavi");
        student.setEmail("ali.alavi@example.com");

        when(studentMapper.toEntity(any(StudentDTO.class))).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        StudentDTO savedStudentDTO = studentService.saveStudent(studentDTO);
        assertNotNull(savedStudentDTO);
        assertEquals("Ali Alavi", savedStudentDTO.getName());
        assertEquals("ali.alavi@example.com", savedStudentDTO.getEmail());
    }

    @Test
    void testGetAllStudents() {
        Student student = new Student();
        student.setName("Ali Alavi");
        student.setEmail("ali.alavi@example.com");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Ali Alavi");
        studentDTO.setEmail("ali.alavi@example.com");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> studentPage = new PageImpl<>(List.of(student));

        when(studentRepository.findAll(pageable)).thenReturn(studentPage);
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        Page<StudentDTO> result = studentService.getAllStudents(0, 10);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Ali Alavi", result.getContent().get(0).getName());
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setName("Ali Alavi");
        student.setEmail("ali.alavi@example.com");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Ali Alavi");
        studentDTO.setEmail("ali.alavi@example.com");

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        StudentDTO result = studentService.getStudentById(1L);
        assertNotNull(result);
        assertEquals("Ali Alavi", result.getName());
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCalculateGPA() {
        Student student = new Student();
        Grade grade1 = new Grade();
        grade1.setScore(90);
        Grade grade2 = new Grade();
        grade2.setScore(80);
        student.setGrades(List.of(grade1, grade2));

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        double gpa = studentService.calculateGPA(1L);
        assertEquals(85.0, gpa);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setName("Ali Alavi");
        student.setEmail("ali.alavi@example.com");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Jane Alavi");
        studentDTO.setEmail("jane@example.com");

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDto(any(Student.class))).thenReturn(studentDTO);

        StudentDTO updatedStudentDTO = studentService.updateStudent(1L, studentDTO);
        assertNotNull(updatedStudentDTO);
        assertEquals("Jane Alavi", updatedStudentDTO.getName());
        assertEquals("jane@example.com", updatedStudentDTO.getEmail());
    }

}