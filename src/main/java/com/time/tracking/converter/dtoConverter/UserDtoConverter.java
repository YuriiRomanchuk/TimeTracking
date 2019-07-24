package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class UserDtoConverter implements Converter<HttpServletRequest, UserDto> {
    @Override
    public UserDto convert(HttpServletRequest request) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setLogin(request.getParameter("login"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setFullName(request.getParameter("lastName"));
        userDto.setEmail(request.getParameter("email"));
        userDto.setPhone(request.getParameter("phone"));
        return userDto;
    }
}
