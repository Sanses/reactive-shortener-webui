package com.sansae.shorturl.config;

import com.sansae.shorturl.model.Link;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConfigurationProperties
@EnableRedisRepositories("com.sansae.shorturl.repository")
public class RedisConfiguration {

    @Bean
    ReactiveRedisOperations<String, Link> reactiveRedisOperations(
            ReactiveRedisConnectionFactory factory) {

        RedisSerializationContext<String, Link> serializationContext = RedisSerializationContext
                .<String, Link>newSerializationContext(new StringRedisSerializer())
                .hashKey(new StringRedisSerializer())
                .hashValue(new Jackson2JsonRedisSerializer<>(Link.class))
                .build();

        return new ReactiveRedisTemplate(factory, serializationContext);
    }
}