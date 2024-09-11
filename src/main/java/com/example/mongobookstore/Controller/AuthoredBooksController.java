package com.example.mongobookstore.Controller;

import com.example.mongobookstore.APIExceptionHandler.AuthorNotFoundException;
import com.example.mongobookstore.Entity.CreateUpdateBookRequest;
import com.example.mongobookstore.Entity.DTOs.CreateUpdateBookRequestDto;
import com.example.mongobookstore.Mapper.Mapper;
import com.example.mongobookstore.Service.AuthoredBookService;
import lombok.AllArgsConstructor;
import com.example.mongobookstore.Entity.AuthoredBook;
import com.example.mongobookstore.Entity.DTOs.AuthoredBookDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
@NoArgsConstructor
public class AuthoredBooksController {
    @Autowired
    private AuthoredBookService authoredBookService;
    @Autowired
    private Mapper<AuthoredBook, AuthoredBookDTO> bookMapper;
    @Autowired
    private Mapper<CreateUpdateBookRequest, CreateUpdateBookRequestDto> createUpdateRequestMapper;

    @GetMapping
    public List<AuthoredBookDTO> listBooks() {
        return authoredBookService.listBooks()
                .stream().map(bookMapper::mapTo)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthoredBookDTO> getBookByIsbn(@PathVariable final String id) {
        return authoredBookService.getBookById(id)
                .map(book ->
                        ResponseEntity.ok(bookMapper.mapTo(book))
                ).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @PostMapping
    public AuthoredBookDTO createUpdateBook(
            @RequestBody CreateUpdateBookRequestDto requestBody) {
        CreateUpdateBookRequest createUpdateBookRequest = createUpdateRequestMapper.mapFrom(requestBody);
        AuthoredBook createUpdatedBook = authoredBookService.createUpdateBook(createUpdateBookRequest);

        return bookMapper.mapTo(createUpdatedBook);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable String id) {
        authoredBookService.deleteBook(id);
    }
}
