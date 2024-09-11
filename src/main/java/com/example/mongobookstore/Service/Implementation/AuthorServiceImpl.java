package com.example.mongobookstore.Service.Implementation;

import com.example.mongobookstore.Entity.CreateUpdateAuthorRequest;
import com.example.mongobookstore.Entity.Documents.Author;
import com.example.mongobookstore.Repository.AuthorRepo;
import com.example.mongobookstore.Service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public List<Author> listAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(String id) {
        return authorRepo.findById(id);
    }

    @Override
    public Author createUpdateAuthor(CreateUpdateAuthorRequest createUpdateAuthorRequest) {
        return authorRepo.findAuthorByName(createUpdateAuthorRequest.getName()).map(
                existingAuthor -> {
                    Author updatedAuthor = Author.builder()
                            .name(createUpdateAuthorRequest.getName())
                            .build();
                    return authorRepo.save(updatedAuthor);
                }).orElseGet(() -> {
                    Author author = Author.builder()
                            .name(createUpdateAuthorRequest.getName())
                            .build();
                    return authorRepo.save(author);
        });
    }

    @Override
    public void deleteAuthor(String id) {
        authorRepo.deleteById(id);
    }
}
