package com.Alura.Gutenex.Gutenex.Repository;

import com.Alura.Gutenex.Gutenex.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);

    List<Book> findByLanguage(String language);
}
