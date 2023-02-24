package easyb.service.impl;

import java.io.IOException;
import java.util.concurrent.CompletionException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class JsonPropertiesMapper extends com.fasterxml.jackson.databind.ObjectMapper {

    public JsonPropertiesMapper(){
        super();
        // just accept properties mappeable in java. dont fail if something new comes from json
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    /** Parses JSON from http api to EasyProperties. */
    public EasyProperties readValue(String content) {
        try {
            return this.readValue(content, new TypeReference<>(){});
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }
}