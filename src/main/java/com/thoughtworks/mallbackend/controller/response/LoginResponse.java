package com.thoughtworks.mallbackend.controller.response;

import com.thoughtworks.mallbackend.entity.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private Long userId;

    public LoginResponse(Users user) {
        this.userId = user.getId();
    }
}
