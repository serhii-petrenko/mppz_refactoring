package com.rshu.mppz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.List;

public class ListStringConvertor implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> productIds) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(productIds);
        } catch (final JsonProcessingException e) {
//            logger.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public List<String> convertToEntityAttribute(String json) {
        List<String> productIds = null;
        try {
            productIds = objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (final IOException e) {
//            logger.error("JSON reading error", e);
        }

        return productIds;
    }
}
