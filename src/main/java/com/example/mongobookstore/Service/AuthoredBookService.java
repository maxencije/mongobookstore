package com.example.mongobookstore.Service;

import com.example.mongobookstore.Entity.AuthoredBook;
import com.example.mongobookstore.Entity.CreateUpdateBookRequest;

import java.util.List;
import java.util.Optional;

public interface AuthoredBookService {
    List<AuthoredBook> listBooks();
    Optional<AuthoredBook> getBookById(String id);
    AuthoredBook createUpdateBook(CreateUpdateBookRequest createUpdateBookRequest);
    void deleteBook(String id);
}
