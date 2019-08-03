package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserCreateDto;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class UserDtoConverter implements Converter<HttpServletRequest, UserCreateDto> {
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
        return userCreateDto;
    }
}
