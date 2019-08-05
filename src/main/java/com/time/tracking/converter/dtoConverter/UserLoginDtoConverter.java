package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserLoginDto;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class UserLoginDtoConverter implements Converter<HttpServletRequest, UserLoginDto> {
    @Override
    public UserLoginDto convert(HttpServletRequest request) {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail(request.getParameter("email"));
        userLoginDto.setPassword(request.getParameter("password"));
        return userLoginDto;
    }
}
