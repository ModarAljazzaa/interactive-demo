package com.interactive.demo.dto.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateDTO {

    @NotNull
    @Size(min = 4, message = "Title should have at least 4 characters")
    private String title;

    @NotNull
    @Size(min = 4, message = "Description should have at least 4 characters")
    private String description;
}
