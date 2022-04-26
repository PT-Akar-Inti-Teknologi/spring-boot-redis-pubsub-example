package com.test.redispubsub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.redispubsub.constant.TopicConstant;
import com.test.redispubsub.dto.MessageData;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscribe {

  private final ReactiveRedisOperations<String, MessageData> redisMessage;

  private static ObjectMapper objectMapper = new ObjectMapper();

  @PostConstruct
  private void setSubscribe() {
    this.redisMessage.listenTo(ChannelTopic.of(TopicConstant.TOPIC))
        .map(ReactiveSubscription.Message::getMessage)
        .subscribe(this::subscribe);
  }

  private void subscribe(MessageData messageData) {
    log.info("DATA INCOME :" + toString(messageData));
  }

  private String toString(Object o) {
    try {
      return objectMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      return "<failed write>";
    }
  }
}
