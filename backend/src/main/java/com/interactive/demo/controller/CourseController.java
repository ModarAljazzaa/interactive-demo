package com.interactive.demo.controller;

import com.interactive.demo.dto.course.CourseCreateDTO;
import com.interactive.demo.dto.course.CourseMapper;
import com.interactive.demo.dto.course.CourseResponseDTO;
import com.interactive.demo.dto.course.CourseUpdateDTO;
import com.interactive.demo.exception.ResourceNotFoundException;
import com.interactive.demo.model.Course;
import com.interactive.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {


    private final CourseService courseService;

    private final CourseMapper courseMapper;


    @GetMapping()
    public ResponseEntity<List<CourseResponseDTO>> getList(@RequestParam(required = false) String title) {
        List<Course> courseList = courseService.getList(title);
        List<CourseResponseDTO> result = courseMapper.asResponseResponseDtoList(courseList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Course existingCourse = courseService.getById(id);
        CourseResponseDTO responseDTO = courseMapper.asCourseResponseDto(existingCourse);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CourseResponseDTO> create(@Valid @RequestBody CourseCreateDTO courseCreateDTO) {
        Course course = courseMapper.asCourse(courseCreateDTO);
        Course createdCourse = courseService.create(course);
        CourseResponseDTO responseDTO = courseMapper.asCourseResponseDto(createdCourse);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> update(@PathVariable("id") long id, @RequestBody CourseUpdateDTO courseUpdateDTO) throws ResourceNotFoundException {
        Course existingCourse = courseService.getById(id);
        Course updateCourse = courseMapper.asUpdatedCourse(courseUpdateDTO, existingCourse);
        Course course = courseService.update(updateCourse);
        CourseResponseDTO responseDTO = courseMapper.asCourseResponseDto(course);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") long id) throws ResourceNotFoundException {
        Course existingCourse = courseService.getById(id);
        courseService.deleteById(existingCourse.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", String.format("Course with id %s deleted successfully", id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
