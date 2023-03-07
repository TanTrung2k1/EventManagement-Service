package com.example.em.service.Impl;

import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.listEvent.EventDTO;
import com.example.em.entity.Event;
import com.example.em.entity.Manager;
import com.example.em.repository.EventRepository;
import com.example.em.repository.ManagerRepository;
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
    private ManagerRepository managerRepo;
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

    @Override
    public CEventDTO addEvent(CEventDTO eventDTO) {
        //manager lay tu userlogin
        long id = 16;
        Manager manager = getManagerById(id);

        //
        Event event = modelMapper.map(eventDTO, Event.class);
        event.setManager(manager);
        event.setStatus(true);
        repo.save(event);
        return modelMapper.map(event, CEventDTO.class);
    }
    private Manager getManagerById(Long id){
        return managerRepo.getReferenceById(id);
    }
}
