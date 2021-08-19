package com.svulinovic.library.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.FieldError;

import java.util.List;

public class Helper {

    public static String errorsToString(List<FieldError> errors){
        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (FieldError error : errors ) {
            sb.append(delim).append(error.getField() + " " + error.getDefaultMessage());
            delim = ", ";
        }
        return sb.toString();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object fromJsonToObject(final String json, Class clazz) {
        try {
            if (json != null)
                return new ObjectMapper().readValue(json, clazz);
            else
                return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object fromJsonToListOfObjects(final String json, Class clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (json != null)
                return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
            else
                return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
