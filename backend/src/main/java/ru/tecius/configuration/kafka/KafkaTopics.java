package ru.tecius.configuration.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.kafka.topics")
@Data
public class KafkaTopics {

  private String userUpdate;

}
