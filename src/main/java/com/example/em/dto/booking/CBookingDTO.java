package com.example.em.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CBookingDTO {
    private Long eventId;
    private Long userId;
    private LocalDateTime timeBooking;
}
