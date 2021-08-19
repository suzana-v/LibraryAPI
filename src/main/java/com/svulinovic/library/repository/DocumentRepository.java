package com.svulinovic.library.repository;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class DocumentRepository {

    private static final String SAVE_OCR_RESULT_CALL = "OcrResult_Create";

    private final SimpleJdbcCall saveOrcResultCall;

    public DocumentRepository(DataSource dataSource) {
        saveOrcResultCall = new SimpleJdbcCall(dataSource).withProcedureName(SAVE_OCR_RESULT_CALL);
    }

    public void saveOcrResult(Map<String, Object> inParam) {
        saveOrcResultCall.execute(inParam);
    }

}
