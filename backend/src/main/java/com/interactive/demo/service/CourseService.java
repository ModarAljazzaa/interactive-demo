package com.interactive.demo.service;

import com.interactive.demo.dto.course.CourseMapper;
import com.interactive.demo.dto.course.CourseUpdateDTO;
import com.interactive.demo.exception.ResourceNotFoundException;
import com.interactive.demo.model.Course;
import com.interactive.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {


    private final CourseRepository courseRepository;


    private final CourseMapper courseMapper;

    public List<Course> getList(String title) {

        List<Course> courseList = new ArrayList<>();

        if (title == null)
            courseList.addAll(courseRepository.findAll());
        else
            courseList.addAll(courseRepository.findByTitle(title));

        return courseList;
    }

    public Course getById(Long id) throws ResourceNotFoundException {
     return courseRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException(String.format("Course with id %s not found", id)));
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course update(Course updatedCourse) {
        return courseRepository.save(updatedCourse);
    }

}
