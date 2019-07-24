package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.entityConverter.UserConverter;
import com.time.tracking.model.dto.UserDto;
import com.time.tracking.model.entity.User;
import com.time.tracking.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

@InitializeComponent
public class UserService {

    private final UserConverter userConverter;
    private List<User> users = new ArrayList<>();

    public UserService(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public void createUser(UserDto userDto) {
        User user = userConverter.convert(userDto);
        user.setRole(receiveRoleForUser());
        users.add(user);
    }

    private Role receiveRoleForUser() {
        return users.size() > 0 ? Role.USER : Role.ADMIN;
    }
}

