package com.example.academy.core.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Consumer;

public class BaseHelper {

    public static <T> void updateIfPresent(T value, Consumer<T> updater) {
        if (value != null && !(value instanceof String && ((String) value).isBlank())) {
            updater.accept(value);
        }
    }

    public static void updateIfPresent(String dateOfBirth, Consumer<LocalDate> updater) {
        if (dateOfBirth != null && !dateOfBirth.isBlank()) {
            try {
                LocalDate parsedDate = LocalDate.parse(dateOfBirth);
                updater.accept(parsedDate);
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format: " + dateOfBirth);
            }
        }
    }

}
