package com.example.em.service;

import com.example.em.dto.listEvent.EventDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEventService {
    List<EventDTO> getAll();
}
