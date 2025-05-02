package com.example.academy.core.excaption;

public class PasswordNotMatchesException extends RuntimeException {
    public PasswordNotMatchesException(String s) {
        super(s);
    }
}
