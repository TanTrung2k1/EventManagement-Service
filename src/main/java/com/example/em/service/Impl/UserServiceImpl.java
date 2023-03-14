package com.example.em.service.Impl;

import com.example.em.dto.response.UserLoginDTO;
import com.example.em.dto.user.CreateUserDTO;
import com.example.em.dto.user.UserDTO;
import com.example.em.entity.User;
import com.example.em.repository.UserRepository;
import com.example.em.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserLoginDTO checkLogin(String email, String password) {
        UserLoginDTO result = null;
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            result = modelMapper.map(user, UserLoginDTO.class);
        }
        return result;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> userList = userRepository.findByStatusTrue();
        List<UserDTO> userDTOList = new ArrayList<>();
        if(!userList.isEmpty()) {
            for(User user: userList) {
                userDTOList.add(modelMapper.map(user, UserDTO.class));
            }
        }
        return userDTOList;
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> userFetchResult = userRepository.findByIdAndStatusTrue(id);
        return userFetchResult.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }

    @Override
    public UserDTO addUser(CreateUserDTO createUserDTO) {
        User user = modelMapper.map(createUserDTO, User.class);
        if(userRepository.findByEmailAndStatusTrue(user.getEmail()).isEmpty()) {
            user.setStatus(true);
            return modelMapper.map(userRepository.save(user), UserDTO.class);
        }
        return null;
    }

    public Boolean deleteUserById(Long id) {
        Optional<User> fetchResult = userRepository.findByIdAndStatusTrue(id);
        if(fetchResult.isPresent()) {
            User deletedUser = fetchResult.get();
            deletedUser.setStatus(false);
            userRepository.save(deletedUser);
            return true;
        }
        return false;
    }
}