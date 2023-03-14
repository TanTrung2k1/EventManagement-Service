package com.example.em.controller;

import com.example.em.config.Author;
import com.example.em.dto.response.ResponseObject;
import com.example.em.dto.user.CreateUserDTO;
import com.example.em.dto.user.UserDTO;
import com.example.em.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<ResponseObject> getAll(HttpSession httpSession) {
        if(Author.isAuthorOfAdmin(httpSession)) {
            List<UserDTO> result = userService.getAll();
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ResponseObject(HttpStatus.OK.toString(), "List user", result));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Admin only", null));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getUserById(HttpSession httpSession, @PathVariable Long id) {
        if(Author.isAuthorOfAdmin(httpSession) || Author.isAuthorOfUser(httpSession) || Author.isAuthorOfManager(httpSession)) {
            UserDTO result = userService.getUserById(id);
            if(result != null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "User details", result));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject(HttpStatus.NOT_FOUND.toString(), "User with ID " + id + " not found", null));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Unauthorized", null));
    }

    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody CreateUserDTO createUserDTO) {
        UserDTO result = userService.addUser(createUserDTO);
        if(result != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(new ResponseObject(HttpStatus.CREATED.toString(), "User created successfully", result));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "User with name " + createUserDTO.getName() + " already exist", null));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteById(HttpSession httpSession, @PathVariable Long id) {
        if(Author.isAuthorOfAdmin(httpSession)) {
            boolean isDeleted = userService.deleteUserById(id);
            if(isDeleted) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "User with ID " + id + " deleted successfully", null));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject(HttpStatus.NOT_FOUND.toString(), "User with ID " + id + " not found", null));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Admin only", null));
    }
}
