package com.test.redispubsub.config;

import com.test.redispubsub.dto.MessageData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisPubSubConfig {
  @Bean
  public ReactiveRedisOperations<String, MessageData> redisMessage(
      LettuceConnectionFactory lettuceConnectionFactory) {
    RedisSerializer<MessageData> valueSerializer = new Jackson2JsonRedisSerializer<MessageData>(MessageData.class);
    RedisSerializationContext<String, MessageData> serializationContext =
        RedisSerializationContext.<String, MessageData>newSerializationContext(RedisSerializer.string())
            .value(valueSerializer)
            .build();
    return new ReactiveRedisTemplate<>(lettuceConnectionFactory, serializationContext);
  }
}
