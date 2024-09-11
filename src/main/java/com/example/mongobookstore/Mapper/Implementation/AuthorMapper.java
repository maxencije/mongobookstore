package com.example.mongobookstore.Mapper.Implementation;

import com.example.mongobookstore.Entity.DTOs.AuthorDTO;
import com.example.mongobookstore.Entity.Documents.Author;
import com.example.mongobookstore.Mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<Author, AuthorDTO> {
    @Override
    public AuthorDTO mapTo(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    @Override
    public Author mapFrom(AuthorDTO authorDTO) {
        return Author.builder()
                .id(authorDTO.getId())
                .name(authorDTO.getName())
                .build();
    }
}
