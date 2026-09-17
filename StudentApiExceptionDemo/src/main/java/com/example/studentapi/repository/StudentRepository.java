package com.example.studentapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentapi.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}
