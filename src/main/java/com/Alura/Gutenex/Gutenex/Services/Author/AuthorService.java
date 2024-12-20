package com.Alura.Gutenex.Gutenex.Services.Author;

import com.Alura.Gutenex.Gutenex.Entities.Author;
import com.Alura.Gutenex.Gutenex.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService{

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findByName(String Name) {
        return authorRepository.findByName(Name);
    }

    @Override
    public List<Author> findAuthorByYear(int year) {
        return authorRepository.findAuthorsWithYear(year);
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void printAuthor(Author author) {
        System.out.printf("""
                ------------AUTHOR--------------
                Id: %s
                Name: %s
                Birth Year: %s
                Death Year: %s
                --------------------------------
                """,
                author.getId(),
                author.getName(),
                author.getBirthYear() != null ? author.getBirthYear() : "N/A",
                author.getDeathYear() != null ? author.getDeathYear() : "N/A"
        );
    }

    @Override
    public void displayAllAuthors() {
        var authors = getAllAuthors();
        if(authors.isEmpty()) {
            System.out.println("No authors found in the database.");
            return;
        }
        authors.forEach(this::printAuthor);
    }

    @Override
    public void displayAuthorsByYear(int year) {
        var authors = findAuthorByYear(year);
        if(authors.isEmpty()) {
            System.out.println("There are no authors with that time period.");
        }else {
            authors.forEach(this::printAuthor);
        }
    }
}
