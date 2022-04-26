package com.test.redispubsub.controller;

import com.test.redispubsub.dto.MessageData;
import com.test.redispubsub.service.RedisPublish;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis-pubsub")
@RequiredArgsConstructor
public class TesController {

  private final RedisPublish redisPublish;

  @PostMapping("/tes")
  public ResponseEntity<String> testPublish(@RequestBody MessageData messageData) {
    redisPublish.publish(messageData);
    return ResponseEntity.ok("ok");
  }
}
