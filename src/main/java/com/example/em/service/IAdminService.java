package com.example.em.service;

import com.example.em.dto.response.AdminLoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface IAdminService {
    AdminLoginDTO checkLogin(String userName, String password);
}
