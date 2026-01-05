package com.mediazen.chat.controller;

import com.mediazen.chat.domain.Room;
import com.mediazen.chat.domain.RoomRequest;
import com.mediazen.chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public Mono<Room> createRoom(@RequestBody RoomRequest request) {
        return roomService.createRoom(request.name());
    }

    @GetMapping
    public Flux<Room> getRooms() {
        return roomService.getRooms();
    }
}
