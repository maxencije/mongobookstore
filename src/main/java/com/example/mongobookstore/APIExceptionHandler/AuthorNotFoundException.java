package com.example.mongobookstore.APIExceptionHandler;

import lombok.Getter;

public class AuthorNotFoundException extends BookStoreRequestException {

    private static String ERROR_MESSAGE = "An author with ID '%s' does not exist";

    @Getter
    private String authorId;

    public AuthorNotFoundException(final String authorId) {
        super(String.format(ERROR_MESSAGE, authorId));
        this.authorId = authorId;
    }

    public AuthorNotFoundException(final String authorId, final Throwable cause) {
        super(String.format(ERROR_MESSAGE, authorId), cause);
        this.authorId = authorId;
    }
}
