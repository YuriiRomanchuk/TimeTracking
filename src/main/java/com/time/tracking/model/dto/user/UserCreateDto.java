package com.time.tracking.model.dto.user;

import com.time.tracking.model.enums.Role;

import java.util.Objects;

public class UserCreateDto{

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDto userCreateDto = (UserCreateDto) o;
        return id == userCreateDto.id &&
                Objects.equals(login, userCreateDto.login) &&
                Objects.equals(password, userCreateDto.password) &&
                Objects.equals(firstName, userCreateDto.firstName) &&
                Objects.equals(lastName, userCreateDto.lastName) &&
                Objects.equals(middleName, userCreateDto.middleName) &&
                Objects.equals(email, userCreateDto.email) &&
                Objects.equals(phone, userCreateDto.phone) &&
                role == userCreateDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, middleName, email, phone, role);
    }
}
