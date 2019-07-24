package com.time.tracking.model.dto;

import com.time.tracking.model.enums.Role;

import java.util.Objects;

public class UserDto {

    private int id;
    private String login;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private Role role;


    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id &&
                login.equals(userDto.login) &&
                password.equals(userDto.password) &&
                fullName.equals(userDto.fullName) &&
                email.equals(userDto.email) &&
                phone.equals(userDto.phone) &&
                role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, fullName, email, phone, role);
    }
}
