package com.example.school.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String subject;
    private int score;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}