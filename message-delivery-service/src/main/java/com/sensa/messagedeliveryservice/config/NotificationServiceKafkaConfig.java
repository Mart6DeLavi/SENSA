package com.sensa.messagedeliveryservice.config;

import com.sensa.messagedeliveryservice.dto.kafka.AnswerForNotificationService;
import com.sensa.messagedeliveryservice.dto.kafka.NotificationKafkaDelivery;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationServiceKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, AnswerForNotificationService> notificationServiceProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, AnswerForNotificationService> notificationServiceKafkaTemplate() {
        return new KafkaTemplate<>(notificationServiceProducerFactory());
    }

    @Bean
    public ConsumerFactory<String, NotificationKafkaDelivery> notificationServiceKafkaConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationKafkaDelivery> notificationServiceKafkaContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationKafkaDelivery> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(notificationServiceKafkaConsumerFactory());
        return factory;
    }
}
