package com.mediazen.chat.domain;

public record ChatMessageRequest(
        String roomId,
        String sender,
        String content
) {
}
