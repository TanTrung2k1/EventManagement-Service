package com.example.em.service.Impl;

import com.example.em.dto.listEvent.EventDTO;
import com.example.em.entity.Event;
import com.example.em.repository.EventRepository;
import com.example.em.service.IEventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventServiceImpl implements IEventService {
    @Autowired
    private EventRepository repo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<EventDTO> getAll() {
        List<Event> list = repo.findAll();
        List<EventDTO> result = new ArrayList<>();
        for(Event event : list){
            EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
            result.add(eventDTO);
        }
        return result;
    }
}
