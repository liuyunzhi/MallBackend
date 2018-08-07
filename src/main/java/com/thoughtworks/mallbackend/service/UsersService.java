package com.thoughtworks.mallbackend.service;

import com.thoughtworks.mallbackend.controller.request.UserRequest;
import com.thoughtworks.mallbackend.entity.Users;
import com.thoughtworks.mallbackend.exception.UserNotFoundException;
import com.thoughtworks.mallbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;


    public Users validate(UserRequest userRequest) {
        return usersRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(UserNotFoundException::new);
    }
}
