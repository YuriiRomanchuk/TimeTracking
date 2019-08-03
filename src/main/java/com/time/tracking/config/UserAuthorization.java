package com.time.tracking.config;

import com.time.tracking.model.enums.Role;

import java.util.Objects;

public class UserAuthorization {

    private String email;
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthorization that = (UserAuthorization) o;
        return Objects.equals(email, that.email) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

}
