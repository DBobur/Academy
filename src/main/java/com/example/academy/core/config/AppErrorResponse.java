package com.example.academy.core.config;

import java.time.LocalDateTime;

public record AppErrorResponse(String errorPath, String errorMessage, Integer errorCode, LocalDateTime now) {
}
