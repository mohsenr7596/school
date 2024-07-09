package com.example.school.controller;

import com.example.school.dto.GradeDTO;
import com.example.school.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
@Tag(name = "Grade Management", description = "APIs for managing grades")
public class GradeController {
    private final GradeService gradeService;

    @PostMapping
    @Operation(summary = "Create a new grade")
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO) {
        GradeDTO savedGradeDTO = gradeService.saveGrade(gradeDTO);
        return new ResponseEntity<>(savedGradeDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all grades")
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        List<GradeDTO> grades = gradeService.getAllGrades();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a grade by ID")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id) {
        GradeDTO grade = gradeService.getGradeById(id);
        return new ResponseEntity<>(grade, grade != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a grade by ID")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @RequestBody GradeDTO gradeDTO) {
        GradeDTO updatedGradeDTO = gradeService.updateGrade(id, gradeDTO);
        return new ResponseEntity<>(updatedGradeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a grade by ID")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}