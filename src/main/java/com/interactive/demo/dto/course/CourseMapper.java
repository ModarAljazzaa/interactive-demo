package com.interactive.demo.dto.course;


import com.interactive.demo.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CourseMapper {

    Course asCourse(CourseCreateDTO courseCreateDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    CourseResponseDTO asCourseResponseDto(Course course);

    List<CourseResponseDTO> asResponseResponseDtoList(List<Course> courseList);

    Course asUpdatedCourse(CourseUpdateDTO course, @MappingTarget Course existingCourse);

}
