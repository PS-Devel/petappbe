package com.ps.emailservice.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.emailservice.consumers.PetMessage;
import com.ps.emailservice.consumers.RegistrationMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.ps.emailservice.config.ConsumerTopic.AUTHEMAILTOPIC;

@Slf4j
public class CustomMessageDeserializer implements Deserializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object deserialize(String key, byte[] data) {
        try {
            if (Objects.isNull(data)) {
                log.trace("No data to deserialize");
                return null;
            }
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), getDeserializerClass(key));
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[]");
        }
    }

    private Class getDeserializerClass(String key) {
        Class clazz;

        switch (key) {
            case AUTHEMAILTOPIC:
                clazz = RegistrationMessage.class;
                break;
            default:
                return null;
        }

        // Non voglio che si possa passare qualsiasi cosa, il messaggio deve implementare l'interfaccia PetMessage.
        return PetMessage.class.isAssignableFrom(clazz)
                ? clazz
                : null;
    }

}
