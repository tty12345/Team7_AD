package com.example.demo.domain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class MyJsonDeserializer extends JsonDeserializer<Category> {

    @Override
    public Category deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        String name = node.get("Category").asText("Default");
        int quota = node.get("Quota").asInt(0);
        int premium = node.get("Premium").asInt(0);

        return new Category(name, quota, premium);
    }

}
