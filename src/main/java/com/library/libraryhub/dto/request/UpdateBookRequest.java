package com.library.libraryhub.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateBookRequest {

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "L'auteur est obligatoire")
    private String author;

    private String description;

    @Min(value = 1, message = "Il faut au moins 1 exemplaire")
    private int totalCopies;

    private Long categoryId;
}