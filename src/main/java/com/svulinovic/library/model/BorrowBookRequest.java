package com.svulinovic.library.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BorrowBookRequest {

    @NotNull
    private Integer userId;

    @Valid
    @NotEmpty
    private List<BorrowedBook> borrowedBooks;

}
