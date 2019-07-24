package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class UserDtoConverter implements Converter<HttpServletRequest, UserDto> {
    @Override
    public UserDto convert(HttpServletRequest object) throws Exception {
        return null;
    }
}
