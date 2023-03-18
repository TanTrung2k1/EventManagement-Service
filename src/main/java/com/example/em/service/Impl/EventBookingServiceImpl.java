package com.example.em.service.Impl;

import com.example.em.dto.booking.BookingDTO;
import com.example.em.dto.booking.CBookingDTO;
import com.example.em.dto.response.AdminLoginDTO;
import com.example.em.dto.response.UserLoginDTO;
import com.example.em.entity.*;
import com.example.em.repository.EventBookingRepository;
import com.example.em.repository.EventRepository;
import com.example.em.repository.UserRepository;
import com.example.em.service.IEventBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EventBookingServiceImpl implements IEventBookingService {
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private EventBookingRepository bookingRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepo;
    @Override
    public CBookingDTO createBooking(CBookingDTO bookingDTO) {
        User user = userRepo.getReferenceById(bookingDTO.getUserId());
        Event event = getEventById(bookingDTO.getEventId());
        if(event == null){
            return null;
        }
        if(event.getBookings().stream().anyMatch(booking -> booking.getUser().getId().equals(user.getId()))){
            return null;
        }

        EventBooking booking = EventBooking.builder()
                .event(event)
                .timeBooking(bookingDTO.getTimeBooking())
                .user(user)
                .status(true)
                .build();
        bookingRepo.save(booking);
        return bookingDTO;
    }

    @Override
    public Boolean cancelBooking(Long id) {
        boolean result = false;
        Optional<EventBooking> optionalEventBooking = bookingRepo.findById(id);
        if(optionalEventBooking.isPresent()){
            EventBooking booking = optionalEventBooking.get();
            booking.setStatus(false);
            bookingRepo.save(booking);
            result = true;
        }
        return result;
    }

    @Override
    public List<BookingDTO> getAllBookingOfEvent(Long id) {

        Optional<Event> optionalEvent = eventRepo.findById(id);
        if(optionalEvent.isPresent()){
            List<BookingDTO> listBooking = new ArrayList<>();
            Event event = optionalEvent.get();
            List<EventBooking> listEventBooking = event.getBookings();
            for (EventBooking book: listEventBooking) {
                BookingDTO bookingDTO = modelMapper.map(book, BookingDTO.class);
                bookingDTO.setEventId(id);
                listBooking.add(bookingDTO);
            }
            return listBooking;
        }
        return null;

    }

    private User getUserInSession(HttpSession session){
        UserLoginDTO userDTO = (UserLoginDTO) session.getAttribute("US");
        User user = userRepo.getReferenceById(userDTO.getId());
        return user;
    }

    private Event getEventById(Long id){
        Event result = null;
        Optional<Event> optionalEvent = eventRepo.findById(id);
        if(optionalEvent.isPresent()){
            result = optionalEvent.get();
        }
        return result;
    }
}
