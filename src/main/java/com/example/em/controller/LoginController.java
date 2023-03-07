package com.example.em.controller;

import com.example.em.dto.request.LoginDTO;
import com.example.em.dto.response.AdminLoginDTO;
import com.example.em.dto.response.ManagerLoginDTO;
import com.example.em.dto.response.ResponseObject;
import com.example.em.dto.response.UserLoginDTO;
import com.example.em.entity.Admin;
import com.example.em.service.IAdminService;
import com.example.em.service.IManagerService;
import com.example.em.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IManagerService managerService;

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(HttpServletRequest request, @RequestBody LoginDTO login){

        //check previous login information
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // clear previous login information
        }

        // create a new session
        session = request.getSession(true);

        AdminLoginDTO admin = adminService.checkLogin(login.getEmail(), login.getPassword());
        if (admin != null){
            session.setAttribute("AD", admin);
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Login success!! Welcome admin", admin);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        ManagerLoginDTO manager = managerService.checkLogin(login.getEmail(), login.getPassword());
        if(manager != null){
            session.setAttribute("MA", manager);
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Login success!! Welcome manager", manager);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        UserLoginDTO user = userService.checkLogin(login.getEmail(), login.getPassword());
        if(user != null){
            session.setAttribute("US", user);
            ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Login success!! Welcome user", user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ResponseObject response = new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Invalid credentials!", login);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseObject> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        ResponseObject response = new ResponseObject(HttpStatus.OK.toString(), "Logout success!!", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
