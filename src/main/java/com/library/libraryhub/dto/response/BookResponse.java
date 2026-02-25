package com.library.libraryhub.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String description;
    private int totalCopies;
    private int availableCopies;
    private boolean available;
    private String categoryName;
}