package com.library.libraryhub.repository;

import com.library.libraryhub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByNameIgnoreCase(String name);
    Boolean existsByNameIgnoreCase(String isbn);

}
