package com.svulinovic.library.service;

import com.svulinovic.library.model.BookHistoryResponse;
import com.svulinovic.library.model.BorrowBookRequest;
import com.svulinovic.library.repository.BookRepository;
import com.svulinovic.library.util.Helper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void borrowBook(BorrowBookRequest request) {
        Map<String, Object> inParam = new HashMap<>();
        inParam.put("userId", request.getUserId());
        inParam.put("borrowedBooks",  Helper.asJsonString(request.getBorrowedBooks()));
        bookRepository.borrowBook(inParam);
    }

    public BookHistoryResponse getBookHistory(Integer id) {
        Map<String, Object> inParam = new HashMap<>();
        inParam.put("bookId", id);
        return bookRepository.getBookHistory(inParam);
    }

}
