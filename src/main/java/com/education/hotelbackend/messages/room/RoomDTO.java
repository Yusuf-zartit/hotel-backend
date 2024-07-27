package com.education.hotelbackend.messages.room;

import com.education.hotelbackend.model.Room;
import com.education.hotelbackend.util.BlobUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.UUID;

@Slf4j
public record RoomDTO(UUID id, String roomType, BigDecimal roomPrice, boolean isBooked, String image) {


    public static RoomDTO of(Room room) {
        Blob roomImageBlob = room.getRoomImage();
        String imageBase64 = BlobUtil.blobToString(roomImageBlob);

        return new RoomDTO(
                room.getId(),
                room.getRoomType(),
                room.getRoomPrice(),
                room.isBooked(),
                imageBase64);
    }
}
