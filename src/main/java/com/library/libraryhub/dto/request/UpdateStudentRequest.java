package com.library.libraryhub.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStudentRequest {

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;
}
