package com.example.em.service;

import com.example.em.dto.response.UserLoginDTO;
import com.example.em.dto.user.CreateUserDTO;
import com.example.em.dto.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<UserDTO> getAll();
    UserDTO getUserById(Long id);
    UserDTO addUser(CreateUserDTO createUserDTO);
    Boolean deleteUserById(Long id);
    UserLoginDTO checkLogin(String email, String password);
}
