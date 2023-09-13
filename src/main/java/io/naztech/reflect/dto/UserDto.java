package io.naztech.reflect.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {
    String fullName;
    String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
