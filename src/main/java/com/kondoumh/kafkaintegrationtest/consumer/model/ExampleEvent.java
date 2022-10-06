package com.kondoumh.kafkaintegrationtest.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleEvent {
  @JsonProperty("event_id")
  Long eventId;
  @JsonProperty("name")
  String name;
}
