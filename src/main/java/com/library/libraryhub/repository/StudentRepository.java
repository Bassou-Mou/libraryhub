package com.library.libraryhub.repository;

import com.library.libraryhub.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentNumber(String studentNumber);
    Optional<Student> findByUserEmail(String email);

    @Query("""
        SELECT s FROM Student s
        WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
        OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    Page<Student> searchByName(@Param("name") String name, Pageable pageable);
}