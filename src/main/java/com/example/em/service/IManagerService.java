package com.example.em.service;

import com.example.em.dto.manager.CreateManagerDTO;
import com.example.em.dto.manager.ManagerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IManagerService {
    CreateManagerDTO addManager(CreateManagerDTO managerDTO);
    List<ManagerDTO> getAll();
    Boolean deleteManagerById(Long id);
    ManagerDTO getManagerById(Long id);
}
