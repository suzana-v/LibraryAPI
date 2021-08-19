package com.svulinovic.library.model;

import lombok.Data;

import java.util.List;

@Data
public class BookHistoryResponse {

    private String bookTitle;
    private List<HistoryEntry> history;

}
