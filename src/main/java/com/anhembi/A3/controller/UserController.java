package com.anhembi.A3.controller;

import com.anhembi.A3.model.dto.LoginDTO;
import com.anhembi.A3.model.dto.RegisterDTO;
import com.anhembi.A3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody RegisterDTO registerDTO) {
        userService.userRegister(registerDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        return userService.userLogin(loginDTO);
    }
}