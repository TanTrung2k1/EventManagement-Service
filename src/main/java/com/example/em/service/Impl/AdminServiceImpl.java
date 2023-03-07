package com.example.em.service.Impl;

import com.example.em.dto.response.AdminLoginDTO;
import com.example.em.entity.Admin;
import com.example.em.repository.AdminRepository;
import com.example.em.service.IAdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public AdminLoginDTO checkLogin(String userName, String password) {
        AdminLoginDTO result = null;
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameAndPassword(userName, password);
        if (optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();
            result = modelMapper.map(admin, AdminLoginDTO.class);
        }
        return result;
    }
}
