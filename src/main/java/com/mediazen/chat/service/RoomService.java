package com.mediazen.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediazen.chat.domain.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final ReactiveStringRedisTemplate redisTemplate;
    private final ObjectMapper mapper;
    private static final String ROOM_KEY = "rooms";
    private static final String ROOM_ID_KEY = "rooms:seq";

    public Mono<Room> createRoom(String name) {
        return redisTemplate.opsForValue().increment(ROOM_ID_KEY)
                .flatMap(id -> {
                    String fieldId = String.valueOf(id);
                    Room room = new Room(fieldId, name, Instant.now());
                    return Mono.fromCallable(() -> mapper.writeValueAsString(room))
                            .flatMap(roomJson -> redisTemplate.opsForHash().put(ROOM_KEY, fieldId, roomJson))
                            .thenReturn(room)
                            .onErrorMap(err -> new RuntimeException("JSON conversion error", err));
                });
    }

    public Flux<Room> getRooms() {
        return redisTemplate.opsForHash().values(ROOM_KEY)
                .flatMap(obj ->
                        Mono.fromCallable(() -> mapper.readValue(obj.toString(), Room.class)))
                .onErrorMap(err -> new RuntimeException("JSON conversion error", err));
    }
}
