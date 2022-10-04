package com.kondoumh.kafkaintegrationtest.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ExampleListener {

  private static final Logger logger = LoggerFactory.getLogger(ExampleListener.class);

  @KafkaListener(topics = "TestTopic")
  public void recieve(ConsumerRecord<?, ?> consumerRecord) {
    logger.info("received payload='{}'", consumerRecord.toString());
  }
}
