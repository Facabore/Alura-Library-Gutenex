package com.Alura.Gutenex.Gutenex;

import com.Alura.Gutenex.Gutenex.Helpers.MenuOptions;
import com.Alura.Gutenex.Gutenex.Services.Author.AuthorService;
import com.Alura.Gutenex.Gutenex.Services.Book.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class GutenexApplication implements CommandLineRunner {

	private AuthorService authorService;
	private BookService bookService;

	public GutenexApplication(AuthorService authorService, BookService bookService) {
		this.authorService = authorService;
		this.bookService = bookService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GutenexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			MenuOptions menuOptions = new MenuOptions(bookService, authorService);
			menuOptions.showMenu();
		}catch (Exception e){
			throw e;
		}


	}
}
