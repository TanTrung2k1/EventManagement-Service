package com.example.em.service;

import com.example.em.dto.booking.BookingDTO;
import com.example.em.dto.booking.CBookingDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface IEventBookingService {
    CBookingDTO createBooking(CBookingDTO bookingDTO);
    Boolean cancelBooking(Long id);
    List<BookingDTO> getAllBookingOfEvent(Long id);
}
