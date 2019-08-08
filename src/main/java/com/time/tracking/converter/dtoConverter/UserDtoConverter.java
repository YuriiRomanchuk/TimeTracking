package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.user.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class UserDtoConverter implements Converter<HttpServletRequest, UserDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public UserDto convert(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setId((Integer) request.getSession().getAttribute("user_id") );
        userDto.setLogin(request.getParameter("login"));
        userDto.setFirstName(request.getParameter("firstName"));
        userDto.setLastName(request.getParameter("lastName"));
        userDto.setEmail(request.getParameter("email"));
        userDto.setPhone(request.getParameter("phone"));
        LOGGER.debug("Usr dto is converted");
        return userDto;

    }

/*    private UserDto convertFullRequest(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setLogin(request.getParameter("login"));
        userDto.setFirstName(request.getParameter("firstName"));
        userDto.setLastName(request.getParameter("lastName"));
        userDto.setEmail(request.getParameter("email"));
        userDto.setPhone(request.getParameter("phone"));
        LOGGER.debug("Usr dto is converted");
        return userDto;
    }

    public UserDto convertFromRequestForUserId(HttpServletRequest request) {
        int userId = (Integer) request.getSession().getAttribute("user_id");
        LOGGER.debug("User dto is converted from id!");
        return convertByUserId(userId);
    }

    private UserDto convertByUserId(int userId) {
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        LOGGER.debug("User dto is converted from id!");
        return userDto;
    }*/

}
