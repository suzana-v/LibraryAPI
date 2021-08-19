package com.svulinovic.library.controller;

import com.svulinovic.library.exception.BadRequestException;
import com.svulinovic.library.model.BookHistoryResponse;
import com.svulinovic.library.model.BorrowBookRequest;
import com.svulinovic.library.service.BookService;
import com.svulinovic.library.util.Helper;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/borrow", produces = MediaType.APPLICATION_JSON_VALUE)
    public void borrowBook(@Valid @RequestBody BorrowBookRequest request, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            throw new BadRequestException(Helper.errorsToString(errors));
        }

        bookService.borrowBook(request);
    }

    @GetMapping(value = "/{id}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookHistoryResponse getBookHistory(@PathVariable Integer id) {
        return bookService.getBookHistory(id);
    }

}
