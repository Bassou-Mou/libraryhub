package com.library.libraryhub.dto.response;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {    private Long id;
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String email;
    private int totalBorrows;
    private int activeBorrows;
}
