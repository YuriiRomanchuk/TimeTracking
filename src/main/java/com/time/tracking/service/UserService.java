package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.entityConverter.UserCreateConverter;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dao.UserDao;
import com.time.tracking.model.dto.user.UserCreateDto;
import com.time.tracking.model.dto.user.UserLoginDto;
import com.time.tracking.model.entity.User;
import com.time.tracking.model.enums.Role;

@InitializeComponent
public class UserService {

    private final UserDao userDao;
    private final UserCreateConverter userCreateConverter;

    public UserService(UserDao userDao, UserCreateConverter userCreateConverter) {
        this.userDao = userDao;
        this.userCreateConverter = userCreateConverter;
    }

    public void createUser(UserCreateDto userCreateDto) {
        User user = userCreateConverter.convert(userCreateDto);
        user.setRole(receiveRoleForUser());
        userDao.createUser(user);
    }

    public Role receiveUserRole(UserLoginDto userLoginDto) {
        return userDao.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .map(User::getRole)
                .orElse(Role.UNKNOWN);
    }

    public int receiveUserId(UserLoginDto userLoginDto) {
        return userDao.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .map(User::getId)
                .orElse(null);
    }

    private Role receiveRoleForUser() {
        return userDao.findAll().size() > 0 ? Role.USER : Role.ADMIN;
    }

    public User loginUser(UserLoginDto userLoginDto) throws ServiceException {
        try {
            return userDao.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()).orElseThrow(IllegalArgumentException::new);
        } catch (Exception e) {
            throw new ServiceException("Login or password is not correct", e);
        }
    }
}

