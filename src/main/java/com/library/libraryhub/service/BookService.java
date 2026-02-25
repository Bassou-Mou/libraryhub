package com.library.libraryhub.service;


import com.library.libraryhub.dto.request.CreateBookRequest;
import com.library.libraryhub.dto.request.UpdateBookRequest;
import com.library.libraryhub.dto.response.*;
import com.library.libraryhub.entity.Book;
import com.library.libraryhub.entity.Category;
import com.library.libraryhub.exception.BadRequestException;
import com.library.libraryhub.exception.ResourceNotFoundException;
import com.library.libraryhub.repository.BookRepository;
import com.library.libraryhub.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public BookResponse createBook(CreateBookRequest request ) {
        if(bookRepository.existsByIsbn(request.getIsbn())) {
            throw new BadRequestException("Book with ISBN " + request.getIsbn() + " already exists");
        }
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository
                    .findById(request.getCategoryId())
            .orElseThrow(() -> new BadRequestException("Category with ID " + request.getCategoryId() + " not found"));
        }
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .description(request.getDescription())
                .totalCopies(request.getTotalCopies())
                .availableCopies(request.getTotalCopies())
                .category(category)
                .deleted(false)
                .build();
        return toResponse(bookRepository.save(book));
    }
    public PageResponse<BookResponse> getBooks(String title, String author, Long categoryId,int page,int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<Book> bookPage = bookRepository.searchBooks(title,author,categoryId,  pageable);
        return PageResponse.<BookResponse>builder()
                .content(bookPage.getContent()
                                    .stream()
                                    .map(this::toResponse)
                                    .toList())
                .page(bookPage.getNumber())
                .size(bookPage.getSize())
                .totalElements(bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .last(bookPage.isLast())
                .build();
    }

    public BookResponse getBookById(Long id){
        Book book = bookRepository
                .findById(id)
                .filter(b -> !b.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livre introuvable avec l'id : " + id
                ));
        return toResponse(book);
    }

    @Transactional
    public BookResponse  updateBook(Long id, UpdateBookRequest request){
        Book book = bookRepository
                .findById(id)
                .filter(b -> !b.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livre introuvable avec l'id : " + id
                ));
        int diff = request.getTotalCopies() - book.getTotalCopies();
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository
                    .findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Catégorie introuvable avec l'id : "
                                    + request.getCategoryId()
                    ));
        }
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(book.getAvailableCopies() + diff);
        book.setCategory(category);
        return toResponse(bookRepository.save(book));
    }


    public BookResponse deleteBook(Long id){
        Book book = bookRepository
                .findById(id)
                .filter(b -> !b.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livre introuvable avec l'id : " + id
                ));
        book.setDeleted(true);
        return toResponse(book);
    }


    private BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .description(book.getDescription())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .available(book.getAvailableCopies() > 0)
                .categoryName(book.getCategory() != null
                        ? book.getCategory().getName()
                        : null)
                .build();
    }
}
