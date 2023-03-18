package com.example.em.service.Impl;

import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.event.DEventDTO;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            //String path = event.getFilePath();
            //int lastBackslashIndex = path.lastIndexOf('\\');
            //String filenameWithExtension = path.substring(lastBackslashIndex + 1);
            //String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            //String fileUrl = baseUrl + "/image/" + filenameWithExtension;

            EventDTO eventDTO = modelMapper.map(event, EventDTO.class);

            //eventDTO.setFile_path(fileUrl);
            result.add(eventDTO);
        }
        return result;
    }

    @Override
    public CEventDTO addEvent(CEventDTO eventDTO) {
        Manager manager = managerRepo.getReferenceById(eventDTO.getManagerId());
        Event event = modelMapper.map(eventDTO, Event.class);
        event.setManager(manager);
        event.setStatus(true);
        repo.save(event);
        CEventDTO result =  modelMapper.map(event, CEventDTO.class);
        result.setManagerId(eventDTO.getManagerId());
        return result;
    }

    private Manager getManagerInSession(HttpSession session){
        ManagerLoginDTO managerDTO = (ManagerLoginDTO) session.getAttribute("MA");
        Manager manager = managerRepo.getReferenceById(managerDTO.getId());
        return manager;
    }
//    @Override
//    public CEventDTO addEvent(CEventDTO eventDTO, HttpSession session, MultipartFile file) {
//
//        Manager manager = getManagerInSession(session);
//        Event event = modelMapper.map(eventDTO, Event.class);
//        event.setManager(manager);
//        event.setStatus(true);
//        repo.save(event);
//
//        return modelMapper.map(event, CEventDTO.class);
//    }

//    public DEventDTO addEvent(CEventDTO eventDTO, HttpSession session, MultipartFile file) {
//        // create new Event entity from DTO
//        Event event = new Event();
//        event.setName(eventDTO.getName());
//        event.setLocation(eventDTO.getLocation());
//        event.setStartTime(eventDTO.getStartTime());
//        event.setEndTime(eventDTO.getEndTime());
//        event.setDesc(eventDTO.getDesc());
//        event.setStatus(true);
//
//        // get the currently logged in manager and set as the event's manager
//        Manager manager = getManagerInSession(session);
//        event.setManager(manager);
//
//        // check if file is empty
//        if (file.isEmpty()) {
//            return null;
//        }
//
//        // generate file name using UUID to ensure uniqueness
//        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//
//        // Get the path to the project's image directory
//        String projectDir = System.getProperty("user.dir");
//        Path imageDir = Paths.get(projectDir, "images");
//
//        // create file path using the image directory and generated file name
//        String filePath = imageDir.resolve(fileName).toString();
//
//        // save file to disk
//        try {
//            Path path = Paths.get(filePath);
//            Files.write(path, file.getBytes());
//        } catch (IOException e) {
//            // handle file write error
//            return null;
//        }
//
//        // set file path in the event entity
//        event.setFilePath(filePath);
//
//        // save event entity to database
//        repo.save(event);
//
//        // create URL to view uploaded image
//        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
//        String fileUrl = baseUrl + "/image/" + fileName;
//
//        DEventDTO result = DEventDTO.builder()
//                .name(event.getName())
//                .location(event.getLocation())
//                .startTime(event.getStartTime())
//                .endTime(event.getEndTime())
//                .desc(event.getDesc())
//                .file_path(fileUrl)
//                .build();
//        // convert the saved entity back to DTO and return it
//        return result;
//    }

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

    @Override
    public Event getEventById(Long eventId) {
        return repo.findById(eventId).get();
    }

    private Manager getManagerById(Long id){
        return managerRepo.getReferenceById(id);
    }
}
