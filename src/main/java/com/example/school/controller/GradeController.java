package com.example.school.controller;

import com.example.school.dto.GradeDTO;
import com.example.school.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
@Tag(name = "Grade Management", description = "APIs for managing grades")
public class GradeController {
    private final GradeService gradeService;

    @PostMapping
    @Operation(summary = "Create a new grade")
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO) {
        final var savedGradeDTO = gradeService.saveGrade(gradeDTO);
        return new ResponseEntity<>(savedGradeDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all grades with pagination")
    public ResponseEntity<Page<GradeDTO>> getAllGrades(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        final var grades = gradeService.getAllGrades(page, size);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a grade by ID")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id) {
        final var grade = gradeService.getGradeById(id);
        return new ResponseEntity<>(grade, grade != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a grade by ID")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @RequestBody GradeDTO gradeDTO) {
        final var updatedGradeDTO = gradeService.updateGrade(id, gradeDTO);
        return new ResponseEntity<>(updatedGradeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a grade by ID")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}