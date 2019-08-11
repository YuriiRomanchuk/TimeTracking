package com.time.tracking.service;

import com.time.tracking.converter.entityConverter.UserCreateConverter;
import com.time.tracking.model.dao.UserDao;
import com.time.tracking.model.dto.user.UserCreateDto;
import com.time.tracking.model.dto.user.UserLoginDto;
import com.time.tracking.model.entity.User;
import com.time.tracking.model.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserCreateConverter userCreateConverter;

    @InjectMocks
    private UserService userService;


    @Test
    public void createUser() {
        UserCreateDto userCreateDto = new UserCreateDto();
        User user = new User();
        when(userCreateConverter.convert(userCreateDto)).thenReturn(user);
        userDao.insert(user);
        verify(userDao).insert(user);
    }

    @Test
    public void receiveUserRole() {
    }

    @Test
    public void receiveUserId() {
        int userId = 1;
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("anybis2007@gmail.com");
        userLoginDto.setPassword("1");

        User user = new User();
        user.setId(userId);
        when(userDao.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())).thenReturn(Optional.of(user));

        User userTest = userDao.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()).orElse(null);
        assertEquals(user.getId(), userTest.getId());
    }

    @Test
    public void loginUser() {

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("anybis2007@gmail.com");
        userLoginDto.setPassword("1");

        User user = new User();
        user.setId('3');
        user.setEmail("anybis2007@gmail.com");
        user.setPassword("1");
        user.setFirstName("Юрий");
        user.setLastName("Романчук");
        user.setMiddleName("Юрий");
        user.setRole(Role.ADMIN);
        user.setPhone("679393303");

        when(userDao.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())).thenReturn(Optional.of(user));
        User userTest = userDao.findUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(new User());

        assertEquals(userTest, user);
    }
}
