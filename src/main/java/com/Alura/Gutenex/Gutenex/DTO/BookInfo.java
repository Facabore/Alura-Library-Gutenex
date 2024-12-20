package com.Alura.Gutenex.Gutenex.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookInfo(@JsonAlias("title") String title, @JsonAlias("authors") List<AuthorInfo> author, @JsonAlias("languages") List<String> language,
                       @JsonAlias("download_count") Long downloadsCount) {

}
