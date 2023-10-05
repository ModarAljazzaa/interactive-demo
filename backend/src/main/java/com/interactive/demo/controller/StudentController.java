package com.interactive.demo.controller;

import com.interactive.demo.dto.student.StudentCreateDTO;
import com.interactive.demo.dto.student.StudentMapper;
import com.interactive.demo.dto.student.StudentResponseDTO;
import com.interactive.demo.dto.student.StudentUpdateDTO;
import com.interactive.demo.exception.ResourceNotFoundException;
import com.interactive.demo.model.Course;
import com.interactive.demo.model.Student;
import com.interactive.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;

    @GetMapping()
    public ResponseEntity<List<StudentResponseDTO>> getList(@RequestParam(required = false) String title) {
        List<Student> studentList = studentService.getList(title);
        List<StudentResponseDTO> result = studentMapper.asStudentResponseDtoList(studentList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Student studentResult = studentService.getById(id);
        StudentResponseDTO responseDTO = studentMapper.asStudentResponseDto(studentResult);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentCreateDTO studentCreateDTO) {
        Student student = studentMapper.asStudent(studentCreateDTO);
        Student createdStudent = studentService.create(student);
        StudentResponseDTO responseDTO = studentMapper.asStudentResponseDto(createdStudent);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable("id") long id, @RequestBody StudentUpdateDTO studentUpdateDTO) throws ResourceNotFoundException {
        Student existingStudent = studentService.getById(id);
        Student updatedStudent = studentMapper.asUpdatedStudent(studentUpdateDTO, existingStudent);
        Student createdStudent = studentService.update(updatedStudent);
        StudentResponseDTO responseDTO = studentMapper.asStudentResponseDto(createdStudent);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") long id) throws ResourceNotFoundException {
        Student existingStudent = studentService.getById(id);
        studentService.deleteById(existingStudent.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", String.format("Student with id %s deleted successfully", id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
