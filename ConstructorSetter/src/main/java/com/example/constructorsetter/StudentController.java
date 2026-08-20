package com.example.constructorsetter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/")
    public String studentDetails() {

        Student student = new Student("Akshat Aryan");

        student.setRollNo(201);

        return "Student Name : " + student.getName()
                + "<br>Roll Number : " + student.getRollNo();
    }
}