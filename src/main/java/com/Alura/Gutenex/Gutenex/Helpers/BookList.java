package com.Alura.Gutenex.Gutenex.Helpers;

import com.Alura.Gutenex.Gutenex.DTO.BookInfo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookList(@JsonAlias("results") List<BookInfo> books){

}
