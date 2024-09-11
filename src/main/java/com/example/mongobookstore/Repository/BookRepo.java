package com.example.mongobookstore.Repository;

import com.example.mongobookstore.Entity.Documents.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends MongoRepository<Book, String> {
    Optional<Book> findBookByTitle(String title);
}