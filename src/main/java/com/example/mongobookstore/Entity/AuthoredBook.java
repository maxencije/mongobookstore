package com.example.mongobookstore.Entity;

import com.example.mongobookstore.Entity.Documents.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthoredBook {
    private String id;
    private String title;
    private Author author;
}
