package com.interactive.demo.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
}
