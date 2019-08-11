package com.time.tracking.converter.entityConverter;

import com.time.tracking.converter.dtoConverter.UserCreateDtoConverter;
import com.time.tracking.model.dto.user.UserCreateDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserCreateConverterTest {

    @InjectMocks
    private UserCreateDtoConverter userCreateDtoConverter;

    @Test
    public void convert() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getParameter("login")).thenReturn("decorator");
        when(request.getParameter("firstName")).thenReturn("Elvis");
        when(request.getParameter("lastName")).thenReturn("Presley");
        when(request.getParameter("middleName")).thenReturn("Elvisovich");
        when(request.getParameter("email")).thenReturn("elvis@gmail.com");
        when(request.getParameter("phone")).thenReturn("123123");
        when(request.getParameter("password")).thenReturn("1");

        UserCreateDto userCreateDto = receiveUserDtoTest();
        UserCreateDto convert = userCreateDtoConverter.convert(request);

        Assert.assertTrue(new ReflectionEquals(convert).matches(userCreateDto));
    }

    private UserCreateDto receiveUserDtoTest() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setEmail("elvis@gmail.com");
        userCreateDto.setFirstName("Elvis");
        userCreateDto.setLastName("Presley");
        userCreateDto.setMiddleName("Elvisovich");
        userCreateDto.setLogin("decorator");
        userCreateDto.setPhone("123123");
        userCreateDto.setPassword("1");

        return userCreateDto;
    }
}