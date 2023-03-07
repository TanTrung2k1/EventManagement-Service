package com.example.em.controller;

import com.example.em.dto.listEvent.EventDTO;
import com.example.em.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
