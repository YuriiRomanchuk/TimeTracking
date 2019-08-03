package com.time.tracking.converter.dtoConverter;

import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserLoginDto;

import javax.servlet.http.HttpServletRequest;

public class UserLoginDtoConverter implements Converter<HttpServletRequest, UserLoginDto> {
    @Override
    public UserLoginDto convert(HttpServletRequest object) {
        return null;
    }
}
