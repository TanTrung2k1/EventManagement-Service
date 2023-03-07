package com.example.em.service;

import com.example.em.dto.response.UserLoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    UserLoginDTO checkLogin(String email, String password);
}
