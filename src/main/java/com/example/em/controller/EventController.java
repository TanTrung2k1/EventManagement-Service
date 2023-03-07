package com.example.em.controller;

import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.listEvent.EventDTO;
import com.example.em.dto.manager.CreateManagerDTO;
import com.example.em.dto.response.ResponseObject;
import com.example.em.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private IEventService service;

    @GetMapping
    public List<EventDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody CEventDTO eventDTO){
        CEventDTO result = service.addEvent(eventDTO);
        ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Event created successfully", result);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
