package com.interactive.demo.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateDTO {

    @NotNull
    @Size(min = 2, message = "First Name should have at least 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last Name should have at least 2 characters")
    private String lastName;

    @Email
    @NotBlank
    private String emailId;
}
