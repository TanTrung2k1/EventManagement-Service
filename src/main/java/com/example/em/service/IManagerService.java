package com.example.em.service;

import com.example.em.dto.manager.CreateManagerDTO;
import com.example.em.dto.manager.ManagerDTO;
import com.example.em.dto.response.ManagerLoginDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface IManagerService {
    CreateManagerDTO addManager(CreateManagerDTO managerDTO);
    List<ManagerDTO> getAll();
    Boolean deleteManagerById(Long id);
    ManagerDTO getManagerById(Long id);
    ManagerLoginDTO checkLogin(String email, String password);
}
