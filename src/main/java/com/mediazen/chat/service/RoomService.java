package com.mediazen.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final ReactiveStringRedisTemplate redisTemplate;
    private static final String ROOM_KEY = "rooms";
    private static final String ROOM_ID_KEY = "rooms:seq";

    public Mono<String> createRoom(String name) {
        Map<String, String> roomData = Map.of(
                "name", name,
                "createdAt", Instant.now().toString()
        );
        return redisTemplate.opsForValue().increment(ROOM_KEY, 1L)
                .flatMap(id -> Mono.just("room" + id));
    }
}
