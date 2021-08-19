package com.svulinovic.library.service;

import com.svulinovic.library.exception.BadRequestException;
import com.svulinovic.library.model.OcrRequest;
import com.svulinovic.library.model.OcrResponse;
import com.svulinovic.library.parser.mrz.Type1Mrz;
import com.svulinovic.library.parser.mrz.Type1MrzParser;
import com.svulinovic.library.repository.DocumentRepository;
import com.svulinovic.library.retrofit.microblink.MicroblinkClient;
import com.svulinovic.library.retrofit.microblink.model.BlinkIdEndpointResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final MicroblinkClient microblinkClient;

    public DocumentService(DocumentRepository documentRepository, MicroblinkClient microblinkClient) {
        this.documentRepository = documentRepository;
        this.microblinkClient = microblinkClient;
    }

    public OcrResponse readDocument(OcrRequest request) {

        BlinkIdEndpointResponse microblinkResponse = microblinkClient.scanMRZ(request.getImage());

        if (microblinkResponse.getResult().getMrzData() == null) {
            throw new BadRequestException("Invalid document image");
        }

        //"IDCYPCR12345673<<<<<<<<<<<<<<<\n9003185M2503181CYP<<<<<<<<<<<4\nSPECIMEN<<SAMPLE<<<<<<<<<<<<<<\n"
        String rawMrz = microblinkResponse.getResult().getMrzData().getRawMrzString();
        System.out.println("rawMrz: " + rawMrz);

        System.out.println("microblink firstName: " + microblinkResponse.getResult().getMrzData().getSecondaryId());
        System.out.println("microblink lastName: " + microblinkResponse.getResult().getMrzData().getPrimaryId());
        System.out.println("microblink dateOfBirth: " + microblinkResponse.getResult().getMrzData().getDateOfBirth().getOriginalString());
        System.out.println("microblink isValid: " + microblinkResponse.getResult().getMrzData().isMrzVerified());

        Type1Mrz type1Mrz = new Type1MrzParser(rawMrz);

        OcrResponse response = new OcrResponse();
        response.setFirstName(type1Mrz.getSecondaryId());
        response.setLastName(type1Mrz.getPrimaryId());
        response.setDateOfBirth(type1Mrz.getDateOfBirth());
        response.setValid(type1Mrz.isValid());

        System.out.println("firstName: " + type1Mrz.getSecondaryId());
        System.out.println("lastName: " + type1Mrz.getPrimaryId());
        System.out.println("dateOfBirth: " + type1Mrz.getDateOfBirth());
        System.out.println("isValid: " + type1Mrz.isValid());

        Map<String, Object> inParam = new HashMap<>();
        inParam.put("firstName", response.getFirstName());
        inParam.put("lastName", response.getLastName());
        inParam.put("dateOfBirth", response.getDateOfBirth());
        inParam.put("valid", response.isValid());
        documentRepository.saveOcrResult(inParam);

        return response;
    }
}
