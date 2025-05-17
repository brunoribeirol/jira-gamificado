package com.cesar.school.presentation.response;

import java.util.Map;

public record ApiResponse(
        boolean success,
        String message,
        Object data
) {
    public ApiResponse(boolean success, String message) {
        this(success, message, null);
    }
}
