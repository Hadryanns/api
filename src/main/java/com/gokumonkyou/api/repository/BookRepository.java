package com.gokumonkyou.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gokumonkyou.api.model.Book;
import java.util.Optional;
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String Isbn);
    boolean existsByIsbn(String Isbn);
}