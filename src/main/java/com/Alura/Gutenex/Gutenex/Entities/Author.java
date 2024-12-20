package com.Alura.Gutenex.Gutenex.Entities;


import com.Alura.Gutenex.Gutenex.Abstractions.Entity;
import com.Alura.Gutenex.Gutenex.DTO.AuthorInfo;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@jakarta.persistence.Entity
@Table(name= "author")
public class Author extends Entity {

    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @ManyToMany(mappedBy = "author")
    private List<Book> books;

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Author() {
        super();
    }

    public Author(AuthorInfo authorInfo) {
        super();
        this.name = authorInfo.name();
        this.birthYear = authorInfo.birthYear() != null ? Integer.valueOf(authorInfo.birthYear()) : null;
        this.deathYear = authorInfo.deathYear() != null ? Integer.valueOf(authorInfo.deathYear()) : null;
    }
}
