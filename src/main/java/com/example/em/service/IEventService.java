package com.example.em.service;

import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.listEvent.EventDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface IEventService {
    List<EventDTO> getAll();
    CEventDTO addEvent(CEventDTO eventDTO, HttpSession session);
    Boolean deleteEventById(Long id);
}
