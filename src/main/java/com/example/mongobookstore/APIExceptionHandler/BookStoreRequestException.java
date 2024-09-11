package com.example.mongobookstore.APIExceptionHandler;

public class BookStoreRequestException extends RuntimeException {
    public BookStoreRequestException() {
    }

    public BookStoreRequestException(String message) {
        super(message);
    }

    public BookStoreRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
