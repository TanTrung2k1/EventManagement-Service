package com.example.em.service.Impl;

import com.example.em.dto.response.UserLoginDTO;
import com.example.em.entity.User;
import com.example.em.repository.UserRepository;
import com.example.em.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserLoginDTO checkLogin(String email, String password) {
        UserLoginDTO result = null;
        Optional<User> optionalUser = userRepo.findByEmailAndPassword(email, password);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            result = modelMapper.map(user, UserLoginDTO.class);
        }
        return result;
    }
}
