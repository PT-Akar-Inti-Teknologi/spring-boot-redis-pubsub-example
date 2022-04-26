package com.test.redispubsub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.redispubsub.constant.TopicConstant;
import com.test.redispubsub.dto.MessageData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisPublish {

  private final ReactiveRedisOperations<String, MessageData> redisMessage;

  private static ObjectMapper objectMapper = new ObjectMapper();

  public void publish(MessageData messageData) {
    redisMessage.convertAndSend(TopicConstant.TOPIC,messageData).subscribe(aLong -> {
      log.info("PUBLISHED MESSAGE "+aLong+" : "+toString(messageData));
    });
  }

  private String toString(Object o){
    try {
      return objectMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      return "<failed write>";
    }
  }
}
