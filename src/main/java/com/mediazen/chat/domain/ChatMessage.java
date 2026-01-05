package com.mediazen.chat.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record ChatMessage(
        String id,
        String roomId,
        String sender,
        String content,
        Instant timestamp
) {
    public static ChatMessage from(ChatMessageRequest request) {
        return ChatMessage.builder()
                .id(UUID.randomUUID().toString())
                .roomId(request.roomId())
                .sender(request.sender())
                .content(request.content())
                .timestamp(Instant.now())
                .build();
    }
}
