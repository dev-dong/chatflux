package com.mediazen.chat.service;

import com.mediazen.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final Map<String, Sinks.Many<ChatMessage>> rooms = new ConcurrentHashMap<>();

    public void sendMessage(ChatMessage message) {
        String roomId = message.roomId();
        Sinks.Many<ChatMessage> chatSink = rooms.computeIfAbsent(roomId, id -> Sinks.many().multicast().onBackpressureBuffer());
        chatSink.tryEmitNext(message);
        log.info("Sending message to room {}: {}", message.roomId(), message);
    }

    public Flux<ChatMessage> subscribeToRoom(String roomId) {
        Sinks.Many<ChatMessage> chatSink = rooms.computeIfAbsent(roomId, id -> Sinks.many().multicast().onBackpressureBuffer());
        return chatSink.asFlux();
    }
}
