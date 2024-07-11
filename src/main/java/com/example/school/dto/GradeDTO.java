package com.example.school.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;
    private String subject;
    private int score;
    private Long studentId;
}