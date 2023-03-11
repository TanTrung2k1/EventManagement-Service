package com.example.em.controller;

import com.example.em.config.Author;
import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.listEvent.EventDTO;
import com.example.em.dto.manager.CreateManagerDTO;
import com.example.em.dto.response.ManagerLoginDTO;
import com.example.em.dto.response.ResponseObject;
import com.example.em.service.IEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.em.config.Author.isAuthorOfUser;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private IEventService service;

    private Boolean isAuthorOfManager(HttpSession session){
        boolean result = false;
        ManagerLoginDTO user = (ManagerLoginDTO) session.getAttribute("MA");
        if(user != null){
            result = true;
        }
        return result;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAll(HttpSession session) {
        if(isAuthorOfManager(session)){
            List<EventDTO> result = service.getAll();
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "List event", result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Manager only", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
//    public List<EventDTO> getAll(){
//        return service.getAll();
//    }

    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody CEventDTO eventDTO, HttpSession session){
        if(isAuthorOfManager(session)) {
            CEventDTO result = service.addEvent(eventDTO, session);

            if(result != null){
                ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Event create successfully", result);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else{
                ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Can't create event", eventDTO);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Manager only", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteById(HttpSession session, @PathVariable Long id) {
        if (Author.isAuthorOfManager(session)) {
            boolean isDeleted = service.deleteEventById(id);
            if (isDeleted) {
                ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Event with ID " + id + " canceled successfully", null);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Canceled event with ID " + id + " not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }
        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Manager only", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
