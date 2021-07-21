package ru.goodex.service.exceptions;

public class PostNotFoundException extends Throwable {

    public PostNotFoundException(String message) {
        super(message);
    }
}
