package com.library.libraryhub.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "borrows")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrow extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDate borrowDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BorrowStatus status = BorrowStatus.ACTIVE;

    public BigDecimal calculatePenalty() {
        if (returnDate == null || !returnDate.isAfter(dueDate)) {
            return BigDecimal.ZERO;
        }
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return BigDecimal.valueOf(daysLate * 100L);
    }
}