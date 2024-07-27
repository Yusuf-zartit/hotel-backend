package com.education.hotelbackend.messages.room;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomCreateRequest {
    @NotEmpty
    private String roomType;
    @NotEmpty
    private BigDecimal price;
}
