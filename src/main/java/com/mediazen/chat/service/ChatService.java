package com.mediazen.chat.service;

import com.mediazen.chat.domain.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ChatService {
    private final Map<String, Sinks.Many<ChatMessage>> rooms = new ConcurrentHashMap<>();

    public void sendMessage(ChatMessage message) {
        Sinks.Many<ChatMessage> chatSink = rooms.getOrDefault(message.roomId(), Sinks.many().multicast().onBackpressureBuffer());
        chatSink.tryEmitNext(message);
        log.info("Sending message to room {}: {}", message.roomId(), message);
    }
}
