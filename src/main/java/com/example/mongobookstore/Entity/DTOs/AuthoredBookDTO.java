package com.example.mongobookstore.Entity.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthoredBookDTO {
    private String id;
    private String title;
    private AuthorDTO author;
}
