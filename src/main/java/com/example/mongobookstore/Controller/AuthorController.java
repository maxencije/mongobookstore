package com.example.mongobookstore.Controller;

import com.example.mongobookstore.Entity.CreateUpdateAuthorRequest;
import com.example.mongobookstore.Entity.DTOs.AuthorDTO;
import com.example.mongobookstore.Entity.DTOs.CreateUpdateAuthorRequestDto;
import com.example.mongobookstore.Entity.Documents.Author;
import com.example.mongobookstore.Mapper.*;
import com.example.mongobookstore.Service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
@NoArgsConstructor
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private Mapper<Author, AuthorDTO> authorMapper;
    @Autowired
    private Mapper<CreateUpdateAuthorRequest, CreateUpdateAuthorRequestDto> createUpdateRequestMapper;

    @GetMapping
    public List<AuthorDTO> listAuthors() {
        return authorService.listAuthors()
                .stream()
                .map(authorMapper::mapTo)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable String id) {
        return authorService.getAuthorById(id)
                .map(author ->
                        ResponseEntity.ok(authorMapper.mapTo(author))
                ).orElseThrow(
                        () -> new RuntimeException("Author not found")
                );
    }

    @PostMapping
    public AuthorDTO createUpdateAuthor(
            @RequestBody CreateUpdateAuthorRequestDto requestBody) {
        CreateUpdateAuthorRequest createUpdateAuthorRequest = createUpdateRequestMapper.mapFrom(requestBody);
        Author createUpdatedAuthor = authorService.createUpdateAuthor(createUpdateAuthorRequest);

        return authorMapper.mapTo(createUpdatedAuthor);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
    }
}
