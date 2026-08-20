package com.example.StudentCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.StudentCRUD.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
