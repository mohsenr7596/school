package com.example.school.controller;

import com.example.school.domain.Student;
import com.example.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a student by ID")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
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
        double gpa = studentService.calculateGPA(id);
        return new ResponseEntity<>(gpa, HttpStatus.OK);
    }
}