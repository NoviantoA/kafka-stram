package com.noviantoanggoro.kafkastream.sample.broker.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class CustomJsonDeserialize<T> implements Deserializer<T> {

    private ObjectMapper objectMapper;
    private Class<T> deserializedClass;

    public  CustomJsonDeserialize(Class<T> deserializedClass){
        this.deserializedClass = deserializedClass;
    }

    @Override
    public T deserialize(String s, byte[] data) {
        try {
            return objectMapper.readValue(data, deserializedClass);
        } catch (IOException e){
            throw new SerializationException(e.getMessage());
        }
    }
}
