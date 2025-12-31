package com.mediazen.chat.domain;

import lombok.Builder;

@Builder
public record Room(
        String id,
        String name
) {
}
