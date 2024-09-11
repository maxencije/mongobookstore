package com.example.mongobookstore.Entity.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("books")
@Getter
@Setter
public class Book {

    @Id
    private String id;
    private String title;
    private String authorId;
}
