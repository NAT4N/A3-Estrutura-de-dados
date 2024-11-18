package com.anhembi.demo.model.dto;

import com.anhembi.demo.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
