package com.Alura.Gutenex.Gutenex.Services.Book;

import com.Alura.Gutenex.Gutenex.Entities.Book;

import java.util.List;

public interface IBookService {
    Book findByTittle(String tittle);
    List<Book> findByLanguage(String language);
}
