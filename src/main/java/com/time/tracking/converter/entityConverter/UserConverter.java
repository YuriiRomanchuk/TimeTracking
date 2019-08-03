package com.time.tracking.converter.entityConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserCreateDto;
import com.time.tracking.model.entity.User;

@InitializeComponent
public class UserConverter implements Converter<UserCreateDto, User> {
    @Override
    public User convert(UserCreateDto userCreateDto) {
        User user = new User();
        user.setId(userCreateDto.getId());
        user.setLogin(userCreateDto.getLogin());
        user.setPassword(userCreateDto.getPassword());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setMiddleName(userCreateDto.getMiddleName());
        user.setEmail(userCreateDto.getEmail());
        user.setPhone(userCreateDto.getPhone());
        return user;
    }
}
