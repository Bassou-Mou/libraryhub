-- Catégories
INSERT INTO CATEGORIES (name, description, created_at, updated_at) VALUES
                                                                       ('Informatique', 'Livres de programmation et informatique', NOW(), NOW()),
                                                                       ('Mathématiques', 'Algèbre, analyse, statistiques', NOW(), NOW()),
                                                                       ('Sciences', 'Physique, chimie, biologie', NOW(), NOW());

-- Livres (sans ID fixe)
INSERT INTO BOOKS (isbn, title, author, total_copies, available_copies, deleted, category_id, created_at, updated_at) VALUES
                                                                                                                          ('978-0-13-468599-1', 'Clean Code', 'Robert C. Martin', 3, 3, false, 1, NOW(), NOW()),
                                                                                                                          ('978-0-13-235088-4', 'The Pragmatic Programmer', 'David Thomas', 2, 2, false, 1, NOW(), NOW()),
                                                                                                                          ('978-0-13-110362-7', 'The C Programming Language', 'Brian Kernighan', 2, 2, false, 1, NOW(), NOW());

-- Utilisateurs (password = "password" encodé BCrypt)
INSERT INTO USERS (email, password, role, enabled, created_at, updated_at) VALUES
                                                                               ('admin@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBpwTTyU9wMmO2', 'ADMIN', true, NOW(), NOW()),
                                                                               ('librarian@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBpwTTyU9wMmO2', 'LIBRARIAN', true, NOW(), NOW()),
                                                                               ('student@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBpwTTyU9wMmO2', 'STUDENT', true, NOW(), NOW());

-- Étudiant
INSERT INTO STUDENTS (first_name, last_name, student_number, user_id, created_at, updated_at) VALUES
    ('Mouacha', 'Bassou', 'ETU-2026-001', 3, NOW(), NOW());