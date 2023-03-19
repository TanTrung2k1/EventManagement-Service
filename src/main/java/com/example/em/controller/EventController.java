package com.example.em.controller;

import com.example.em.dto.event.CEventDTO;
import com.example.em.dto.event.DEventDTO;
import com.example.em.dto.listEvent.EventDTO;
import com.example.em.dto.manager.ManagerDTO;
import com.example.em.dto.response.ResponseObject;
import com.example.em.service.IEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.em.config.Author.*;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private IEventService service;




    @GetMapping
    public ResponseEntity<ResponseObject> getAll(HttpSession session) {

            List<EventDTO> result = service.getAll();
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "List event", result);
            return ResponseEntity.status(HttpStatus.OK).body(response);


    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getEventById(@PathVariable Long id){

        EventDTO result = service.getEventById(id);
        if(result != null){
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Event details", result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Event with ID " + id + " not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchEventsByName(@RequestParam String name) {
        List<EventDTO> result = service.searchEventsByName(name);
        if(!result.isEmpty()){
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Event details", result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping()
    public ResponseEntity<ResponseObject> updateEvent(@RequestBody DEventDTO eventDTO){
        DEventDTO result = service.updateEvent(eventDTO);
        if(result != null){
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Update success", result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Event with ID " + eventDTO.getId() + " not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


//    @PostMapping
//    public ResponseEntity<ResponseObject> create(@RequestBody CEventDTO eventDTO, HttpSession session){
//        if(isAuthorOfManager(session)) {
//            CEventDTO result = service.addEvent(eventDTO, session);
//
//            if(result != null){
//                ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Event create successfully", result);
//                return ResponseEntity.status(HttpStatus.CREATED).body(response);
//            }else{
//                ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Can't create event", eventDTO);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//        }
//        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Manager only", null);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestParam String name,
                                                @RequestParam String location,
                                                @RequestParam String startTime,
                                                @RequestParam String endTime,
                                                @RequestParam String desc,
                                                @RequestParam String image,
                                                @RequestParam Long managerId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        CEventDTO eventDTO = CEventDTO.builder().name(name).location(location).startTime(LocalDateTime.parse(startTime, formatter)).endTime(LocalDateTime.parse(endTime, formatter)).desc(desc).stringImage(image).managerId(managerId).build();

        CEventDTO result = service.addEvent(eventDTO);
        if(result != null){
            ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Event create successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Can't create event", eventDTO);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

}

//    public ResponseEntity<ResponseObject> createEvent(@RequestBody CreateEventDTO event, HttpSession session){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        CEventDTO eventDTO = CEventDTO.builder().name(event.getName()).location(event.getLocation()).startTime(LocalDateTime.parse(event.getStartTime(), formatter)).endTime(LocalDateTime.parse(event.getEndTime(), formatter)).desc(event.getDesc()).stringImage(event.getStringImage()).build();
//        if(isAuthorOfManager(session)) {
//            CEventDTO result = service.addEvent(eventDTO, session);
//            if(result != null){
//                ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Event create successfully", result);
//                return ResponseEntity.status(HttpStatus.CREATED).body(response);
//            }else{
//                ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Can't create event", eventDTO);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//        }
//        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Manager only", null);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

//    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ResponseObject> create(@RequestParam(value = "name") String name,
//                                                 @RequestParam(value = "location") String location,
//                                                 @RequestParam(value = "startTime") String startTime,
//                                                 @RequestParam(value = "endTime") String endTime,
//                                                 @RequestParam(value = "desc") String desc,
//                                                 @RequestParam(value = "file") MultipartFile file,
//                                                 HttpSession session){
//        if(isAuthorOfManager(session)) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            CEventDTO eventDTO = CEventDTO.builder().name(name).location(location).startTime(LocalDateTime.parse(startTime, formatter)).endTime(LocalDateTime.parse(endTime, formatter)).desc(desc).build();
//            DEventDTO result = service.addEvent(eventDTO, session, file);
//
//            if(result != null){
//                ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Event created successfully", result);
//                return ResponseEntity.status(HttpStatus.CREATED).body(response);
//            }else{
//                ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Can't create event", eventDTO);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//        }
//        ResponseObject response = new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Manager only", null);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteById(HttpSession session, @PathVariable Long id) {

            boolean isDeleted = service.deleteEventById(id);
            if (isDeleted) {
                ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Event with ID " + id + " canceled successfully", null);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Canceled event with ID " + id + " not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

    }

}
