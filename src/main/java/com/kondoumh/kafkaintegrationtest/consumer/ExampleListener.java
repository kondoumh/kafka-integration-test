package com.kondoumh.kafkaintegrationtest.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kondoumh.kafkaintegrationtest.consumer.model.ExampleEvent;

@Component
public class ExampleListener {

  private static final Logger logger = LoggerFactory.getLogger(ExampleListener.class);

  private String payload1;

  private String payload2;

  @KafkaListener(topics = "TestTopic-1")
  public void recieveTopic1(ExampleEvent event) {
    logger.info("received payload='{}'", event.toString());
    payload1 = event.getEventId().toString();
  }

  @KafkaListener(topics = "TestTopic-2")
  public void recieveTopic2(ExampleEvent event) {
    logger.info("received payload='{}'", event.toString());
    payload2 = event.getEventId().toString();
  }

  public String getPayload1() {
    return payload1;
  }

  public String getPayload2() {
    return payload2;
  }
}
