package com.interactive.demo.dto.student;

import com.interactive.demo.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface StudentMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    Student asStudent(StudentCreateDTO studentCreateDTO);


    //    @Mapping(target = "firstName", source = "firstName")
//    @Mapping(target = "lastName", source = "lastName")
    StudentResponseDTO asStudentResponseDto(Student student);

    List<StudentResponseDTO> asStudentResponseDtoList(List<Student> studentList);

    Student asUpdatedStudent(StudentUpdateDTO course, @MappingTarget Student existingStudent);

}
