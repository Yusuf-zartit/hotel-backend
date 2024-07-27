package com.education.hotelbackend.messages.room;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Builder
public record RoomResponse(UUID id, String roomType, BigDecimal roomPrice, boolean isBooked, String image) {


    public static RoomResponse of(RoomDTO dto) {

        return RoomResponse.builder()
                .id(dto.id())
                .roomType(dto.roomType())
                .roomPrice(dto.roomPrice())
                .isBooked(dto.isBooked())
                .image(dto.image())
                .build();
    }
}
