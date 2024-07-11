package com.example.school.repository;

import com.example.school.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByNameContainingAndEmailContaining(String name, String email, Pageable pageable);
}