package com.education.hotelbackend.service;

import com.education.hotelbackend.messages.room.RoomCreateRequest;
import com.education.hotelbackend.messages.room.RoomDTO;
import com.education.hotelbackend.messages.room.RoomUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IRoomService {
    List<RoomDTO> getAllRooms();

    RoomDTO getRoomById(UUID id);

    RoomDTO createRoom(RoomCreateRequest request, MultipartFile file) throws Exception;

    void updateRoom(UUID id, RoomUpdateRequest request);

    void updateImage(UUID id, MultipartFile file) throws Exception;

    void deleteRoom(UUID id);
}
