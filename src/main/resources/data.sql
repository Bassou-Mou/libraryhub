INSERT INTO CATEGORIES (id, name, description, created_at, updated_at) VALUES
                                                                           (1, 'Informatique', 'Livres de programmation et informatique', NOW(), NOW()),
                                                                           (2, 'Mathématiques', 'Algèbre, analyse, statistiques', NOW(), NOW()),
                                                                           (3, 'Sciences', 'Physique, chimie, biologie', NOW(), NOW());

INSERT INTO BOOKS (id, isbn, title, author, total_copies, available_copies, deleted, category_id, created_at, updated_at) VALUES
                                                                                                                              (1, '978-0-13-468599-1', 'Clean Code', 'Robert C. Martin', 3, 3, false, 1, NOW(), NOW()),
                                                                                                                              (2, '978-0-13-235088-4', 'The Pragmatic Programmer', 'David Thomas', 2, 2, false, 1, NOW(), NOW()),
                                                                                                                              (3, '978-0-13-110362-7', 'The C Programming Language', 'Brian Kernighan', 2, 2, false, 1, NOW(), NOW());

INSERT INTO USERS (id, email, password, role, enabled, created_at, updated_at) VALUES
                                                                                   (1, 'admin@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBpwTTyU9wMmO2', 'ADMIN', true, NOW(), NOW()),
                                                                                   (2, 'librarian@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBpwTTyU9wMmO2', 'LIBRARIAN', true, NOW(), NOW()),
                                                                                   (3, 'student@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBpwTTyU9wMmO2', 'STUDENT', true, NOW(), NOW());

INSERT INTO STUDENTS (id, first_name, last_name, student_number, user_id, created_at, updated_at) VALUES
    (1, 'Mouacha', 'Bassou', 'ETU-2026-001', 3, NOW(), NOW());