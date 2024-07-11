package com.example.school.controller;

import com.example.school.dto.StudentDTO;
import com.example.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {
    private final StudentService studentService;


    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        final var savedStudentDTO = studentService.saveStudent(studentDTO);
        return new ResponseEntity<>(savedStudentDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all students with pagination")
    public ResponseEntity<Page<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        final var students = studentService.getAllStudents(page, size);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a student by ID")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        final var student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, student != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a student by ID")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/gpa")
    @Operation(summary = "Calculate GPA of a student by ID")
    public ResponseEntity<Double> calculateGPA(@PathVariable Long id) {
        final var gpa = studentService.calculateGPA(id);
        return new ResponseEntity<>(gpa, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Search students by name and email with pagination")
    public ResponseEntity<Page<StudentDTO>> searchStudents(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        final var students = studentService.searchStudents(name, email, page, size);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}