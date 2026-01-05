package com.mediazen.chat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record Room(
        String id,
        String name,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss",
                timezone = "UTC")
        Instant created
) {
}
