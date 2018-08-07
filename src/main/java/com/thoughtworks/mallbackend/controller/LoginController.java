package com.thoughtworks.mallbackend.controller;

import com.thoughtworks.mallbackend.controller.request.UserRequest;
import com.thoughtworks.mallbackend.controller.response.LoginResponse;
import com.thoughtworks.mallbackend.exception.UserNotFoundException;
import com.thoughtworks.mallbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(new LoginResponse(usersService.validate(userRequest)));
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private void exceptionHandler(UserNotFoundException exception) {
    }
}
