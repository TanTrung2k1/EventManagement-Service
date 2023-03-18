package com.example.em.controller;

import com.example.em.dto.booking.BookingDTO;
import com.example.em.dto.booking.CBookingDTO;

import com.example.em.dto.response.ResponseObject;

import com.example.em.service.IEventBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.em.config.Author.isAuthorOfManager;
import static com.example.em.config.Author.isAuthorOfUser;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private IEventBookingService bookingService;
    @PostMapping
    public ResponseEntity<ResponseObject> createBooking(@RequestBody CBookingDTO bookingDTO){

            CBookingDTO result = bookingService.createBooking(bookingDTO);
            if(result != null){
                ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Manager create successfully", result);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else{
                ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Can't create booking event", bookingDTO);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteById(HttpSession session, @PathVariable Long id) {

            boolean isDeleted = bookingService.cancelBooking(id);
            if (isDeleted) {
                ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Booking with ID " + id + " canceled successfully", null);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Canceled booking with ID " + id + " not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }


    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getAllListBooking(HttpSession session, @PathVariable Long id){

            List<BookingDTO> result = bookingService.getAllBookingOfEvent(id);
            if(result == null){
                ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Event with ID " + id + " not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "List booking of event id " + id, result);
            return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
