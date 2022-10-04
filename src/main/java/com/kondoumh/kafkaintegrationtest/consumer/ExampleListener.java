package com.kondoumh.kafkaintegrationtest.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExampleListener {

  private static final Logger logger = LoggerFactory.getLogger(ExampleListener.class);

  private String payload1;

  private String payload2;

  @KafkaListener(topics = "TestTopic-1")
  public void recieveTopic1(ConsumerRecord<?, ?> consumerRecord) {
    logger.info("received payload='{}'", consumerRecord.toString());
    payload1 = consumerRecord.value().toString();
  }

  @KafkaListener(topics = "TestTopic-2")
  public void recieveTopic2(ConsumerRecord<?, ?> consumerRecord) {
    logger.info("received payload='{}'", consumerRecord.toString());
    payload2 = consumerRecord.value().toString();
  }

  public String getPayload1() {
    return payload1;
  }

  public String getPayload2() {
    return payload2;
  }
}
