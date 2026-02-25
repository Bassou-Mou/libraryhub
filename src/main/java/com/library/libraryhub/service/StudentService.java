package com.library.libraryhub.service;


import com.library.libraryhub.dto.request.UpdateStudentRequest;
import com.library.libraryhub.dto.response.PageResponse;
import com.library.libraryhub.dto.response.StudentResponse;
import com.library.libraryhub.entity.BorrowStatus;
import com.library.libraryhub.entity.Student;
import com.library.libraryhub.exception.ResourceNotFoundException;
import com.library.libraryhub.repository.BorrowRepository;
import com.library.libraryhub.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final BorrowRepository borrowRepository;

    public PageResponse<StudentResponse> getStudents(String name, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());
        Page<Student> studentPage;
        if(name != null && !name.isBlank()){
            studentPage = studentRepository.searchByName(name,pageable);
        }else{
            studentPage = studentRepository.findAll(pageable);
        }
        return PageResponse.<StudentResponse>builder()
                .content(studentPage.getContent()
                        .stream()
                        .map(this::toResponse)
                        .toList())
                .page(studentPage.getNumber())
                .size(studentPage.getSize())
                .totalElements(studentPage.getTotalElements())
                .totalPages(studentPage.getTotalPages())
                .last(studentPage.isLast())
                .build();
    }

    public StudentResponse getStudentById(Long id){
        return(toResponse(findStudentById(id)));
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Étudiant introuvable avec l'id : " + id
                ));
    }

    @Transactional
    public StudentResponse updateStudent(Long id, UpdateStudentRequest request){
        Student student = findStudentById(id);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        return toResponse( studentRepository.save(student));
    }

    private StudentResponse toResponse(Student student) {

        int activeBorrows = borrowRepository
                .findByStudentIdAndStatus(
                        student.getId(),
                        BorrowStatus.ACTIVE
                ).size();

        int totalBorrows = borrowRepository
                .findAllByStudentId(student.getId())
                .size();

        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .studentNumber(student.getStudentNumber())
                .email(student.getUser() != null
                        ? student.getUser().getEmail()
                        : null)
                .activeBorrows(activeBorrows)
                .totalBorrows(totalBorrows)
                .build();
    }
}
