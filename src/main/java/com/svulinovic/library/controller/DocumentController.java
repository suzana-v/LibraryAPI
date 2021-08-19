package com.svulinovic.library.controller;

import com.svulinovic.library.exception.BadRequestException;
import com.svulinovic.library.model.OcrRequest;
import com.svulinovic.library.model.OcrResponse;
import com.svulinovic.library.service.DocumentService;
import com.svulinovic.library.util.Helper;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/ocr", produces = MediaType.APPLICATION_JSON_VALUE)
    public OcrResponse readDocument(@Valid @RequestBody OcrRequest request, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            throw new BadRequestException(Helper.errorsToString(errors));
        }

        return documentService.readDocument(request);
    }
}
