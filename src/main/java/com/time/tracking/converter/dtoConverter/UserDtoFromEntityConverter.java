package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class UserDtoFromEntityConverter implements Converter<User, UserDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        LOGGER.debug("User dto is converted");
        return userDto;
    }
}
