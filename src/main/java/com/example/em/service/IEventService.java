package com.example.em.service;

import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.event.DEventDTO;
import com.example.em.dto.listEvent.EventDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface IEventService {
    List<EventDTO> getAll();
    DEventDTO addEvent(CEventDTO eventDTO, HttpSession session, MultipartFile file);
    Boolean deleteEventById(Long id);
}
