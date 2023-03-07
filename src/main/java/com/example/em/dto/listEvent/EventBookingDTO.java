package com.example.em.dto.listEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventBookingDTO {
    private Long id;
    private LocalDateTime timeBooking;
    private UserDTO user;
    private Boolean status;
}