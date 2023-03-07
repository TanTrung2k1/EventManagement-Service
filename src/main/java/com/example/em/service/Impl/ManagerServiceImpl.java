package com.example.em.service.Impl;

import com.example.em.dto.manager.CreateManagerDTO;
import com.example.em.dto.manager.ManagerDTO;
import com.example.em.dto.response.ManagerLoginDTO;
import com.example.em.entity.Admin;
import com.example.em.entity.Manager;
import com.example.em.repository.AdminRepository;
import com.example.em.repository.ManagerRepository;
import com.example.em.service.IManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerRepository managerRepo;
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CreateManagerDTO addManager(CreateManagerDTO managerDTO) {
        long id = 1;
        Admin admin = getAdminById(id);
        Manager manager = modelMapper.map(managerDTO, Manager.class);
        manager.setAdmin(admin);
        manager.setStatus(true);
        managerRepo.save(manager);
        return modelMapper.map(manager, CreateManagerDTO.class);
    }

    @Override
    public List<ManagerDTO> getAll() {
        //List<Manager> list = managerRepo.findAll();
        List<Manager> list = managerRepo.findByStatus(true);
        List<ManagerDTO> result = new ArrayList<>();
        for(Manager manager : list){
            ManagerDTO managerDTO = modelMapper.map(manager, ManagerDTO.class);
            result.add(managerDTO);
        }
        return result;
    }

    @Override
    public Boolean deleteManagerById(Long id) {
        boolean result = false;
        Optional<Manager> optionalManager = managerRepo.findById(id);
        if(optionalManager.isPresent()){
            Manager manager = optionalManager.get();
            manager.setStatus(false);
            managerRepo.save(manager);
            result = true;
        }
        return result;
    }

    @Override
    public ManagerDTO getManagerById(Long id) {
        ManagerDTO result = null;
        Optional<Manager> optionalManager = managerRepo.findById(id);
        if(optionalManager.isPresent()){
            Manager manager = optionalManager.get();
            result = modelMapper.map(manager, ManagerDTO.class);
        }
        return result;
    }

    @Override
    public ManagerLoginDTO checkLogin(String email, String password) {
        ManagerLoginDTO result = null;
        Optional<Manager> optionalManager = managerRepo.findByEmailAndPassword(email, password);
        if(optionalManager.isPresent()){
            Manager manager = optionalManager.get();
            result = modelMapper.map(manager, ManagerLoginDTO.class);
        }
        return result;
    }


    private Admin getAdminById(Long id){
        return adminRepo.getReferenceById(id);
    }
}
