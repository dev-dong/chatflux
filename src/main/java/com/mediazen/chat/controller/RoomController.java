package com.mediazen.chat.controller;

import com.mediazen.chat.domain.Room;
import com.mediazen.chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public Mono<Room> createRoom() {
        return roomService.createRoom("sobin");
    }

    @GetMapping
    public Flux<Room> getRooms() {
        return roomService.getRooms();
    }
}
