package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.entityConverter.UserConverter;
import com.time.tracking.model.dao.UserDao;
import com.time.tracking.model.dto.user.UserCreateDto;
import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.model.entity.User;
import com.time.tracking.model.enums.Role;

@InitializeComponent
public class UserService {

    private final UserDao userDao;
    private final UserConverter userConverter;

    public UserService(UserDao userDao, UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    public void createUser(UserCreateDto userCreateDto) {
        User user = userConverter.convert(userCreateDto);
        user.setRole(receiveRoleForUser());
        userDao.createUser(user);
    }

    public Role receiveUserRole(UserDto userDto){
        return null;
    }

    public int receiveUserId(UserDto userDto){
        return 0;
    }

    private Role receiveRoleForUser() {
        return userDao.findAll().size() > 0 ? Role.USER : Role.ADMIN;
    }
}

