package com.education.hotelbackend.controller;

import com.education.hotelbackend.messages.CreateResponse;
import com.education.hotelbackend.messages.room.RoomCreateRequest;
import com.education.hotelbackend.messages.room.RoomResponse;
import com.education.hotelbackend.messages.room.RoomUpdateRequest;
import com.education.hotelbackend.service.IRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final IRoomService roomService;

    @GetMapping
    public List<RoomResponse> getAll() {
        return roomService.getAllRooms().stream().map(RoomResponse::of).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse create(
            @RequestPart @Valid RoomCreateRequest request,
            @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {
        return new CreateResponse(roomService.createRoom(request, image).id());
    }

    @GetMapping("/{id}")
    public RoomResponse getById(@PathVariable UUID id) {
        return RoomResponse.of(roomService.getRoomById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody RoomUpdateRequest request) {
        roomService.updateRoom(id, request);
    }

    @PutMapping("/{id}/image")
    public void updateRoomImage(@PathVariable UUID id,
                                @RequestParam("image") MultipartFile image) throws Exception {
        roomService.updateImage(id, image);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        roomService.deleteRoom(id);
    }
}
