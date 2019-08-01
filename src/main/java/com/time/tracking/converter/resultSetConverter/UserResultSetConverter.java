package com.time.tracking.converter.resultSetConverter;

import com.time.tracking.converter.Converter;
import com.time.tracking.model.entity.User;
import com.time.tracking.model.enums.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetConverter implements Converter<ResultSet, User> {

    private static final Logger LOGGER = LogManager.getLogger(UserResultSetConverter.class);

    @Override
    public User convert(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setFullName(resultSet.getString("full_name"));
        user.setId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setPhone(resultSet.getString("phone"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        LOGGER.debug("User result set is converted!");
        return user;
    }
}
