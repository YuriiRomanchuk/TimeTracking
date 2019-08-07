package com.time.tracking.converter.entityConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserCreateDto;
import com.time.tracking.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class UserCreateConverter implements Converter<UserCreateDto, User> {

    private static final Logger LOGGER = LogManager.getLogger(UserCreateConverter.class);

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
        LOGGER.debug("User is converted from user dto!");
        return user;
    }
}
