package com.anhembi.demo.model.dto;

import com.anhembi.demo.model.User;
import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String email;
    private String password;

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
