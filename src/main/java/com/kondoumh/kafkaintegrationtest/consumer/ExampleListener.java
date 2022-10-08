package com.kondoumh.kafkaintegrationtest.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kondoumh.kafkaintegrationtest.consumer.model.ExampleEvent;

@Component
public class ExampleListener {

  private static final Logger logger = LoggerFactory.getLogger(ExampleListener.class);

  private Long receivedEventId;

  @KafkaListener(topics = "test-topic")
  public void receive(ExampleEvent event) {
    logger.info("received payload='{}'", event.toString());
    receivedEventId = event.getEventId();
  }

  public Long getReceivedEventId() {
    return receivedEventId;
  }
}
