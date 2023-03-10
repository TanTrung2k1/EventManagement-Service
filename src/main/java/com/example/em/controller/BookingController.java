package com.example.em.controller;

import com.example.em.dto.booking.BookingDTO;
import com.example.em.dto.booking.CBookingDTO;
import com.example.em.dto.manager.CreateManagerDTO;
import com.example.em.dto.manager.ManagerDTO;
import com.example.em.dto.response.AdminLoginDTO;
import com.example.em.dto.response.ManagerLoginDTO;
import com.example.em.dto.response.ResponseObject;
import com.example.em.dto.response.UserLoginDTO;
import com.example.em.service.IEventBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private IEventBookingService bookingService;
    private Boolean isAuthor(HttpSession session){
        boolean result = false;
        UserLoginDTO user = (UserLoginDTO) session.getAttribute("US");
        if(user != null){
            result = true;
        }
        return result;
    }
    private Boolean isAuthorOfManager(HttpSession session){
        boolean result = false;
        ManagerLoginDTO user = (ManagerLoginDTO) session.getAttribute("MA");
        if(user != null){
            result = true;
        }
        return result;
    }
    @PostMapping
    public ResponseEntity<ResponseObject> createBooking(HttpSession session, @RequestBody CBookingDTO bookingDTO){
        if(isAuthor(session)){
            CBookingDTO result = bookingService.createBooking(bookingDTO, session);
            if(result != null){
                ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Manager create successfully", result);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else{
                ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Can't create booking event", bookingDTO);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "User only", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteById(HttpSession session, @PathVariable Long id) {
        if (isAuthor(session)) {
            boolean isDeleted = bookingService.cancelBooking(id);
            if (isDeleted) {
                ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Booking with ID " + id + " canceled successfully", null);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Canceled booking with ID " + id + " not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }
        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "User only", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getAllListBooking(HttpSession session, @PathVariable Long id){
        if(isAuthorOfManager(session)){
            List<BookingDTO> result = bookingService.getAllBookingOfEvent(id);
            if(result == null){
                ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Event with ID " + id + " not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "List booking of event id " + id, result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Manager only", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
