package com.kondoumh.kafkaintegrationtest.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class EmbeddedKafkaIntegrationTest {

  @Autowired
  public KafkaTemplate<String, String> template;

  @Autowired
  private ExampleListener listener;

  private final String topic1 = "TestTopic-1";

  private final String topic2 = "TestTopic-2";
  
  @Test
  public void recieve() throws Exception {
    String data = "test message1";
    template.send(topic1, data);
    Thread.sleep(15000);
    assertThat(listener.getPayload1()).isEqualTo(data);
  }

  @Test
  public void recieve2() throws Exception {
    String data = "test message2";
    template.send(topic2, data);
    Thread.sleep(15000);
    assertThat(listener.getPayload2()).isEqualTo(data);
  }
}
