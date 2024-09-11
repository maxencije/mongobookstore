package com.example.mongobookstore.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongobookstore.Entity.Documents.Author;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends MongoRepository<Author, String> {
    Optional<Author> findAuthorByName(String name);
}