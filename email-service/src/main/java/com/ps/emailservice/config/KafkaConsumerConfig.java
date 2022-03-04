package com.ps.emailservice.config;

import com.google.common.collect.ImmutableMap;
import com.ps.emailservice.consumers.RegistrationMessage;
import com.ps.emailservice.deserializer.CustomMessageDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

import static com.ps.emailservice.config.ConsumerGroupId.AUTHEMAILGROUP;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    public Map<String, Object> registrationConsumerConfig() {
        return ImmutableMap.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer, JsonDeserializer.TRUSTED_PACKAGES, "consumers",
                ConsumerConfig.GROUP_ID_CONFIG, AUTHEMAILGROUP,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomMessageDeserializer.class);
    }

    @Bean
    public ConsumerFactory<String, RegistrationMessage> registrationConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(registrationConsumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, RegistrationMessage>> registrationFactory(ConsumerFactory<String, RegistrationMessage> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, RegistrationMessage> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
