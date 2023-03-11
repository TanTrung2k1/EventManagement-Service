package com.example.em.service.Impl;

import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.listEvent.EventDTO;
import com.example.em.dto.response.ManagerLoginDTO;
import com.example.em.dto.response.UserLoginDTO;
import com.example.em.entity.Event;
import com.example.em.entity.EventBooking;
import com.example.em.entity.Manager;
import com.example.em.entity.User;
import com.example.em.repository.EventRepository;
import com.example.em.repository.ManagerRepository;
import com.example.em.service.IEventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Manager getManagerInSession(HttpSession session){
        ManagerLoginDTO managerDTO = (ManagerLoginDTO) session.getAttribute("MA");
        Manager manager = managerRepo.getReferenceById(managerDTO.getId());
        return manager;
    }
    @Override
    public CEventDTO addEvent(CEventDTO eventDTO, HttpSession session) {

        Manager manager = getManagerInSession(session);
        Event event = modelMapper.map(eventDTO, Event.class);
        event.setManager(manager);
        event.setStatus(true);
        repo.save(event);

        return modelMapper.map(event, CEventDTO.class);
    }

    @Override
    public Boolean deleteEventById(Long id) {
        boolean result = false;
        Optional<Event> optionalEvent = repo.findById(id);
        if(optionalEvent.isPresent()){
            Event event = optionalEvent.get();
            event.setStatus(false);
            repo.save(event);
            result = true;
        }
        return result;
    }

    private Manager getManagerById(Long id){
        return managerRepo.getReferenceById(id);
    }
}
