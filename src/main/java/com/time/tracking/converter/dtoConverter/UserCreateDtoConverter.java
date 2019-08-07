package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserCreateDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class UserCreateDtoConverter implements Converter<HttpServletRequest, UserCreateDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserCreateDtoConverter.class);

    @Override
    public UserCreateDto convert(HttpServletRequest request) throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setLogin(request.getParameter("login"));
        userCreateDto.setPassword(request.getParameter("password"));
        userCreateDto.setFirstName(request.getParameter("firstName"));
        userCreateDto.setLastName(request.getParameter("lastName"));
        userCreateDto.setMiddleName(request.getParameter("middleName"));
        userCreateDto.setEmail(request.getParameter("email"));
        userCreateDto.setPhone(request.getParameter("phone"));
        LOGGER.debug("Usr create dto is converted");
        return userCreateDto;
    }
}
