package com.Alura.Gutenex.Gutenex.Services.Book;

import com.Alura.Gutenex.Gutenex.DTO.BookInfo;
import com.Alura.Gutenex.Gutenex.Entities.Author;
import com.Alura.Gutenex.Gutenex.Entities.Book;
import com.Alura.Gutenex.Gutenex.Helpers.ApiConnection;
import com.Alura.Gutenex.Gutenex.Helpers.BookList;
import com.Alura.Gutenex.Gutenex.Helpers.DataConversor;
import com.Alura.Gutenex.Gutenex.Repository.BookRepository;
import com.Alura.Gutenex.Gutenex.Services.Author.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService{

    private static final String URL_BASE = "https://gutendex.com/books/";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final DataConversor dataConversor;
    private final ApiConnection apiConnection;


    public BookService(BookRepository bookRepository, AuthorService authorService, DataConversor dataConversor, ApiConnection apiConnection) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.dataConversor = dataConversor;
        this.apiConnection = apiConnection;
    }

    private BookInfo getDataBook(String bookName) {

        var json = apiConnection.getData(URL_BASE+"?search="+bookName.replace(" ","+"));
        BookList data;
        try {
            data = dataConversor.getData(json, BookList.class);
        }catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }

        var bookSearched = data.books().stream()
                .filter(b -> b.title().toUpperCase().contains(bookName.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookName));
        return bookSearched;
    }

    private void printBook(Book book) {
        System.out.printf(
                """
                -----------BOOK INFO------------
                Title: %s
                Language: %s
                Authors: %s
                Downloads: %s
                --------------------------------
                """,
                book.getTitle(),
                book.getLanguage(),
                book.getAuthor().stream()
                        .map(Author::getName)
                        .collect(Collectors.joining(", ")),
                book.getDownloads()
        );
    }

    public void searchBook(String bookName) {
        try {
            BookInfo data = getDataBook(bookName);
            List<Author> authors = data.author().stream()
                    .map(authorInfo -> {
                        return authorService.getAuthorByName(authorInfo.name())
                                .orElseGet(() -> {
                                    Author newAuthor = new Author(authorInfo);
                                    authorService.saveAuthor(newAuthor);
                                    return newAuthor;
                                });
                    }).toList();
            Book book = new Book(data, authors);
            bookRepository.save(book);
            System.out.println(book);
        }catch (Exception e) {
            System.out.println("An error occurred while searching for the book: " + e.getMessage());
        }

    }

    public void listRegisteredBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            System.out.println("No books found in the database.");
        } else {
            books.forEach(this::printBook);
        }
    }

    public List<Book> getBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public void listBooksByLanguage(String language) {
        var books = getBooksByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("No books found for the specified language: " + language);
            return;
        }

        books.forEach(this::printBook);
    }
    @Override
    public Book findByTittle(String tittle) {
        return null;
    }

    @Override
    public List<Book> findByLanguage(String language) {
        return List.of();
    }
}
