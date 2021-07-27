package br.com.lukinhasssss.transacao.config;

import br.com.lukinhasssss.transacao.dto.TransacaoDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    public Map<String, Object> consumerConfigurations() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getKeyDeserializer());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getValueDeserializer());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());
        return properties;
    }

    @Bean
    public ConsumerFactory<String, TransacaoDto> transactionConsumerFactory() {
        StringDeserializer stringDeserializer = new StringDeserializer();
        JsonDeserializer<TransacaoDto> jsonDeserializer = new JsonDeserializer<TransacaoDto>(TransacaoDto.class, false);

        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(), stringDeserializer, jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransacaoDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransacaoDto> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(transactionConsumerFactory());

        return factory;
    }

    @Bean
    public ConsumerAwareListenerErrorHandler listenErrorHandler() {
        return (m, e, c) -> {
            var listen3Exception = e;
            MessageHeaders headers = m.getHeaders();
            c.seek(new org.apache.kafka.common.TopicPartition(
                headers.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                headers.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class)),
                headers.get(KafkaHeaders.OFFSET, Long.class)
            );
            return null;
        };
    }

}
