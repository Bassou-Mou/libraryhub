package com.library.libraryhub.repository;

import com.library.libraryhub.entity.Borrow;
import com.library.libraryhub.entity.BorrowStatus;
import com.library.libraryhub.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByStudentIdAndStatus(Long student_id, BorrowStatus status);

    @Query("SELECT b FROM Borrow b WHERE b.student.id = :studentId")
    List<Borrow> findAllByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT b FROM Borrow b WHERE b.status = 'ACTIVE' AND b.dueDate < :today")
    List<Borrow> findOverdueBorrows(@Param("today") LocalDate today);

    boolean existsByStudentIdAndBookIdAndStatus(Long studentId, Long bookId, BorrowStatus status);
    long countByBookIdAndStatus(Long bookId, BorrowStatus status);
}
