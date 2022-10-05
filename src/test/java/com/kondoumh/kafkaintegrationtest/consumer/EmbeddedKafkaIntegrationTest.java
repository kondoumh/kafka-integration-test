package com.kondoumh.kafkaintegrationtest.consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmbeddedKafkaIntegrationTest {

  @Autowired
  private EmbeddedKafkaBroker broker;
  
  private KafkaTemplate<String, String> template;

  @Autowired
  private ExampleListener listener;

  private static final String TOPIC1 = "TestTopic-1";

  private static final String TOPIC2 = "TestTopic-2";

  @BeforeAll
  void setUp() {
    template = new KafkaTemplate<>(new DefaultKafkaProducerFactory<String, String>(KafkaTestUtils.producerProps(broker)));
  }

  @Test
  public void recieve() throws Exception {
    String data = "test message1";
    template.send(TOPIC1, data);
    Thread.sleep(1000);
    assertThat(listener.getPayload1()).isEqualTo(data);
  }

  @Test
  public void recieve2() throws Exception {
    String data = "test message2";
    template.send(TOPIC2, data);
    Thread.sleep(1000);
    assertThat(listener.getPayload2()).isEqualTo(data);
  }
}
