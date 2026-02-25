package com.library.libraryhub.controller;


import com.library.libraryhub.dto.request.CreateBookRequest;
import com.library.libraryhub.dto.request.UpdateBookRequest;
import com.library.libraryhub.dto.response.BookResponse;
import com.library.libraryhub.dto.response.PageResponse;
import com.library.libraryhub.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.createBook(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById( @PathVariable long id) {
        return ResponseEntity.ok(bookService.getBookById(id));

    }
    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.getBooks(title, author, categoryId, page, size));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable long id, @Valid @RequestBody UpdateBookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
