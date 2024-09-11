package com.example.mongobookstore.Entity.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("authors")
@Setter
@Getter
public class Author {

    @Id
    private String id;
    private String name;
}