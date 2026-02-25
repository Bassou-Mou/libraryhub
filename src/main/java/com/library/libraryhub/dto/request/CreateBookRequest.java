package com.library.libraryhub.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBookRequest {

    @NotBlank(message="Le titre est obligatoire")
    private String title;

    @NotBlank(message="L'auteur est obligatoire")
    private String author;

    @NotBlank(message="Le code Isbn est obligatoire")
    private String isbn;

    private String description;

    @Min(value = 1, message = "Il faut au moins 1 exemplaire")
    private int totalCopies;

    private Long categoryId;
}
