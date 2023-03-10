package com.example.em.dto.booking;

import com.example.em.dto.listEvent.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private Long eventId;
    private LocalDateTime timeBooking;
    private Boolean status;
    private UserDTO user;
}
