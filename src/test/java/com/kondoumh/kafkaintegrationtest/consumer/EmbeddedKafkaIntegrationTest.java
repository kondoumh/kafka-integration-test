package com.kondoumh.kafkaintegrationtest.consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import com.kondoumh.kafkaintegrationtest.consumer.model.ExampleEvent;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmbeddedKafkaIntegrationTest {

  @Autowired
  private EmbeddedKafkaBroker broker;

  private KafkaTemplate<String, ExampleEvent> template;

  @Autowired
  private ExampleListener listener;

  private static final String TOPIC1 = "test-topic";

  @BeforeAll
  void setUp() {
    Map<String, Object> config = KafkaTestUtils.producerProps(broker);
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    template = new KafkaTemplate<>(new DefaultKafkaProducerFactory<String, ExampleEvent>(config));
  }

  @Test
  public void recieve() throws Exception {
    var event = new ExampleEvent(100L, "Alice");
    template.send(TOPIC1, event);
    Thread.sleep(1000);
    assertThat(listener.getReceivedEventId()).isEqualTo(100L);
  }
}
