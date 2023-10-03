package com.interactive.demo.service;

import com.interactive.demo.dto.student.StudentMapper;
import com.interactive.demo.dto.student.StudentUpdateDTO;
import com.interactive.demo.exception.ResourceNotFoundException;
import com.interactive.demo.model.Student;
import com.interactive.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMapper studentMapper;

    public List<Student> getList(String name) {

        List<Student> studentList = new ArrayList<>();

        if (name == null)
            studentList.addAll(studentRepository.findAll());
        else
            studentList.addAll(studentRepository.findByFirstName(name));

        return studentList;
    }

    public Student getById(Long id) throws ResourceNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %s not found", id)));
    }

    public Student create(Student course) {
        return studentRepository.save(course);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student update(Student updateStudent) {
        return studentRepository.save(updateStudent);
    }
}
