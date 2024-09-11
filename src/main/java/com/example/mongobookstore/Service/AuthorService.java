package com.example.mongobookstore.Service;

import com.example.mongobookstore.Entity.Documents.Author;

import java.util.List;
import java.util.Optional;
import com.example.mongobookstore.Entity.CreateUpdateAuthorRequest;

public interface AuthorService {
    List<Author> listAuthors();

    Optional<Author> getAuthorById(String id);

    Author createUpdateAuthor(CreateUpdateAuthorRequest createUpdateAuthorRequest);

    void deleteAuthor(String id);
}
