package com.library.libraryhub.repository;

import com.library.libraryhub.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContainingIgnoreCaseAndDeletedFalse(String title,Pageable pageable);
    Page<Book> findByDeletedFalse(Pageable pageable);
    @Query("SELECT b FROM Book b " +
            "WHERE b.deleted = false " +
            "AND b.availableCopies > 0")
    Page<Book> finfAvaibleBooks(Pageable pageable);

    @Query("SELECT b FROM Book b" +
            " WHERE b.deleted = false" +
            " AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))" +
            "AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))" +
            "AND (:categoryId IS NULL OR b.category.id = :categoryId)")
    Page<Book> searchBooks(@Param("title")String title,
                           @Param("author")String author,
                           @Param("categoryId") Long categoryId,
                           Pageable pageable);
    Optional<Book> findByIsbnAndDeletedFalse(String isbn);
    boolean existsByIsbn(String isbn);
}
