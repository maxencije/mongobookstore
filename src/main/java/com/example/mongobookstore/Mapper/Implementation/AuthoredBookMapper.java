package com.example.mongobookstore.Mapper.Implementation;

import com.example.mongobookstore.Entity.AuthoredBook;
import com.example.mongobookstore.Entity.Documents.Author;
import com.example.mongobookstore.Entity.DTOs.AuthorDTO;
import com.example.mongobookstore.Entity.DTOs.AuthoredBookDTO;
import com.example.mongobookstore.Mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AuthoredBookMapper implements Mapper<AuthoredBook, AuthoredBookDTO> {

    private Mapper<Author, AuthorDTO> authorMapper;

    public AuthoredBookMapper(Mapper<Author, AuthorDTO> authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthoredBookDTO mapTo(AuthoredBook book) {
        return AuthoredBookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(authorMapper.mapTo(book.getAuthor()))
                .build();
    }

    @Override
    public AuthoredBook mapFrom(AuthoredBookDTO bookDTO) {
        return AuthoredBook.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(authorMapper.mapFrom(bookDTO.getAuthor()))
                .build();
    }
}
