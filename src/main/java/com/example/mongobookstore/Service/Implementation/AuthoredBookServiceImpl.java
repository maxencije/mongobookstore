package com.example.mongobookstore.Service.Implementation;

import com.example.mongobookstore.APIExceptionHandler.AuthorNotFoundException;
import com.example.mongobookstore.Entity.AuthoredBook;
import com.example.mongobookstore.Entity.CreateUpdateBookRequest;
import com.example.mongobookstore.Entity.Documents.Author;
import com.example.mongobookstore.Entity.Documents.Book;
import com.example.mongobookstore.Repository.AuthorRepo;
import com.example.mongobookstore.Repository.BookRepo;
import com.example.mongobookstore.Service.AuthoredBookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthoredBookServiceImpl implements AuthoredBookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public List<AuthoredBook> listBooks() {
        List<Book> allBooks = bookRepo.findAll();
        List<String> authorIDs = allBooks.stream().map(Book::getAuthorId).toList();
        List<Author> authors = authorRepo.findAllById(authorIDs);
        Map<String, Author> authorIndex = authors.stream()
                .collect(Collectors.toMap(Author::getId, author -> author));

        return allBooks.stream().map(book -> {
            final String authorId = book.getAuthorId();
            final Author author = authorIndex.get(authorId);
            if (null == author) {
                throw new AuthorNotFoundException(authorId);
            }
            return buildAuthoredBook(book, author);
        }).toList();
    }

    @Override
    public Optional<AuthoredBook> getBookById(String id) {
        return bookRepo.findById(id).map(book -> {
            String authorId = book.getAuthorId();
            Author author = authorRepo.findById(authorId).orElseThrow(() ->
                    new AuthorNotFoundException(authorId));
            return buildAuthoredBook(book, author);
        });
    }

    @Override
    public AuthoredBook createUpdateBook(CreateUpdateBookRequest createUpdateBookRequest) {
        String authorID = createUpdateBookRequest.getAuthorId();
        Optional<Author> existingAuthor = authorRepo.findById(authorID);
        if (existingAuthor.isEmpty()) {
            throw new AuthorNotFoundException(authorID);
        }

        Book book = bookRepo.findBookByTitle(createUpdateBookRequest.getTitle()).map(existingBook -> {
            Book updatedBook = Book.builder()
                    .title(createUpdateBookRequest.getTitle())
                    .authorId(createUpdateBookRequest.getAuthorId())
                    .build();
            return bookRepo.save(updatedBook);
        }).orElseGet(() -> {
            Book newBook = Book.builder()
                    .title(createUpdateBookRequest.getTitle())
                    .authorId(createUpdateBookRequest.getAuthorId())
                    .build();
            return bookRepo.save(newBook);
        });

        return buildAuthoredBook(book, existingAuthor.get());
    }

    @Override
    public void deleteBook(String id) {
        bookRepo.deleteById(id);
    }

    private AuthoredBook buildAuthoredBook(final Book book, final Author author) {
        return AuthoredBook.builder()
                .title(book.getTitle())
                .author(author)
                .build();
    }
}
