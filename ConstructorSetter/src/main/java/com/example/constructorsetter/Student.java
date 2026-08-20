package com.example.constructorsetter;

public class Student {

    private String name;
    private int rollNo;

    // Constructor
    public Student(String name) {
        this.name = name;
    }

    // Setter Method
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Getter for Roll Number
    public int getRollNo() {
        return rollNo;
    }
}