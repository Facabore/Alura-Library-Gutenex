package com.Alura.Gutenex.Gutenex.Services.Author;

import com.Alura.Gutenex.Gutenex.Entities.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService{

    Optional<Author> findByName(String Name);
    List<Author> findAuthorByYear(int year);
    Optional<Author> getAuthorByName(String name);
    List<Author> getAllAuthors();
    void saveAuthor(Author author);
    void printAuthor(Author author);
    void displayAllAuthors();
    void displayAuthorsByYear(int year);
}
