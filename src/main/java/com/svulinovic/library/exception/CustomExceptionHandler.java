package com.svulinovic.library.exception;

import com.svulinovic.library.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UncategorizedSQLException.class)
    public ResponseEntity<ErrorResponse> handleUncategorizedSQLException(Exception e) {
        e.printStackTrace();
        if ("USER_NOT_FOUND".equals(e.getCause().getMessage())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
        } else if ("BOOK_NOT_FOUND".equals(e.getCause().getMessage())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Book not found"));
        } else if ("BOOK_COPY_NOT_FOUND".equals(e.getCause().getMessage())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Book copy not found"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error"));
    }

    @ExceptionHandler(MicroblinkException.class)
    public ResponseEntity<ErrorResponse> handleMicroblinkException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Microblink: " + e.getMessage()));
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error"));
    }

}
