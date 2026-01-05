package com.mediazen.chat.controller;

import com.mediazen.chat.domain.ChatMessage;
import com.mediazen.chat.domain.ChatMessageRequest;
import com.mediazen.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/message")
    public Mono<Void> send(@RequestBody ChatMessageRequest message) {
        ChatMessage chatMessage = ChatMessage.from(message);
        chatService.sendMessage(chatMessage);
        return Mono.empty();
    }

    @GetMapping(value = "/room/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> subscribe(@PathVariable String roomId) {
        return chatService.subscribeToRoom(roomId);
    }
}
