package org.example.backend.exception;

public class PerfumeNotFoundException extends RuntimeException {
    public PerfumeNotFoundException(String message) {
        super(message);
    }
}
