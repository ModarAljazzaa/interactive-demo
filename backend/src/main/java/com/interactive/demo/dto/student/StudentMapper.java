package com.interactive.demo.dto.student;

import com.interactive.demo.model.Student;
import org.mapstruct.*;

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
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student asUpdatedStudent( @MappingTarget Student existingStudent,StudentUpdateDTO studentUpdateDTO);

}
