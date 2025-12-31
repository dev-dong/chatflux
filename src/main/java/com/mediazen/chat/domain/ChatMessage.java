package com.mediazen.chat.domain;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ChatMessage(
        String id,
        String roomId,
        String sender,
        String content,
        Instant timestamp
) {
}
