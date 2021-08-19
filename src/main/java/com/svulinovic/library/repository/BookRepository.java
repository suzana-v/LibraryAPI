package com.svulinovic.library.repository;

import com.svulinovic.library.exception.NotFoundException;
import com.svulinovic.library.model.BookHistoryResponse;
import com.svulinovic.library.model.HistoryEntry;
import com.svulinovic.library.util.Helper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private static final String BORROW_BOOK_CALL = "Book_Borrow";
    private static final String BOOK_HISTORY_CALL = "Book_GetHistory";

    private final SimpleJdbcCall borrowBookCall;
    private final SimpleJdbcCall bookHistoryCall;

    public BookRepository(DataSource dataSource) {
        borrowBookCall = new SimpleJdbcCall(dataSource).withProcedureName(BORROW_BOOK_CALL);
        bookHistoryCall = new SimpleJdbcCall(dataSource).withProcedureName(BOOK_HISTORY_CALL);
    }

    public void borrowBook(Map<String, Object> inParam) {
        borrowBookCall.execute(inParam);
    }

    public BookHistoryResponse getBookHistory(Map<String, Object> inParam) {
        Map result = bookHistoryCall.execute(inParam);
        List list = (List) result.get("#result-set-1");
        if (list.size() > 0) {
            Map r = (Map) list.get(0);
            BookHistoryResponse response = new BookHistoryResponse();
            response.setBookTitle((String) r.get("bookTitle"));
            String historyEntriesJson = (String) r.get("historyEntriesJson");
            List<HistoryEntry> history = (List<HistoryEntry>) Helper.fromJsonToListOfObjects(historyEntriesJson, HistoryEntry.class);
            response.setHistory(history);
            return response;
        }
        throw new NotFoundException("Book history not found");
    }

}
