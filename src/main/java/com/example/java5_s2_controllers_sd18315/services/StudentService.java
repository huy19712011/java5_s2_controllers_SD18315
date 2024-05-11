package com.example.java5_s2_controllers_sd18315.services;

import com.example.java5_s2_controllers_sd18315.entities.Student;
import com.example.java5_s2_controllers_sd18315.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    final
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }
}
