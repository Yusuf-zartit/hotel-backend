package com.education.hotelbackend.service.impl;

import com.education.hotelbackend.messages.room.RoomCreateRequest;
import com.education.hotelbackend.messages.room.RoomDTO;
import com.education.hotelbackend.messages.room.RoomUpdateRequest;
import com.education.hotelbackend.model.Room;
import com.education.hotelbackend.repository.RoomRepository;
import com.education.hotelbackend.service.IRoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {
    private final RoomRepository roomRepository;

    @Transactional
    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(RoomDTO::of).toList();
    }

    @Override
    public RoomDTO getRoomById(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Room.ENTITY_NAME));
        return RoomDTO.of(room);
    }

    @Override
    public RoomDTO createRoom(RoomCreateRequest request, MultipartFile file) throws Exception {
        Room room = new Room();
        room.setRoomType(request.getRoomType());
        room.setRoomPrice(request.getPrice());
        if (file != null) {
            byte[] imageBytes = file.getBytes();
            Blob blob = new SerialBlob(imageBytes);
            room.setRoomImage(blob);
        }
        return RoomDTO.of(roomRepository.save(room));
    }

    @Override
    public void updateRoom(UUID id, RoomUpdateRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Room.ENTITY_NAME));

        room.setRoomPrice(request.getPrice());
        room.setRoomType(request.getRoomType());

        roomRepository.save(room);
    }

    @Override
    public void updateImage(UUID id, MultipartFile file) throws Exception {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Room.ENTITY_NAME));

        if (!file.isEmpty()) {
            byte[] imageBytes = file.getBytes();
            Blob blob = new SerialBlob(imageBytes);
            room.setRoomImage(blob);
        }

        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(UUID id) {
        roomRepository.deleteById(id);
    }
}
