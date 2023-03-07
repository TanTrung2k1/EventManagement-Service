package com.example.em.controller;

import com.example.em.dto.manager.CreateManagerDTO;
import com.example.em.dto.manager.ManagerDTO;
import com.example.em.dto.response.ResponseObject;
import com.example.em.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/manager")
public class ManagerController {
    @Autowired
    private IManagerService service;


    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<ManagerDTO> result = service.getAll();
        ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "List manager", result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getManagerById(@PathVariable Long id){
        ManagerDTO result = service.getManagerById(id);
        if(result != null){
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Manager details", result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Manager with ID " + id + " not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody CreateManagerDTO managerDTO){
        CreateManagerDTO result = service.addManager(managerDTO);
        ResponseObject response = new ResponseObject(HttpStatus.CREATED.toString(), "Manager create successfully", result);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable Long id) {
        boolean isDeleted = service.deleteManagerById(id);
        if (isDeleted) {
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Manager with ID " + id + " deleted successfully", null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Manager with ID " + id + " not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}
