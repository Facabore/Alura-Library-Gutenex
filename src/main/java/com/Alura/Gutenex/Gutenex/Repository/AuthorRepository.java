package com.Alura.Gutenex.Gutenex.Repository;

import com.Alura.Gutenex.Gutenex.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);

    @Query("SELECT auth FROM Author auth WHERE auth.birthYear <= :year AND (auth.deathYear > :year OR auth.deathYear IS NULL)")
    List<Author> findAuthorsWithYear(@Param("year") int year);
}