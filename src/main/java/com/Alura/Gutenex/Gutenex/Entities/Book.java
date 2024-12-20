package com.Alura.Gutenex.Gutenex.Entities;

import com.Alura.Gutenex.Gutenex.Abstractions.Entity;
import com.Alura.Gutenex.Gutenex.DTO.BookInfo;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

@jakarta.persistence.Entity
public class Book extends Entity {
    public String getTitle() {
        return title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public Long getDownloads() {
        return downloads;
    }

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private List<Author> author;
    private String language;
    private Long downloads;

    public Book() {
        super();
    }

    public Book(BookInfo bookInfo, List<Author> authors) {
        super();
        this.title = bookInfo.title();
        this.language = bookInfo.language().get(0);
        this.downloads = bookInfo.downloadsCount();
        this.author = authors;
    }
}
