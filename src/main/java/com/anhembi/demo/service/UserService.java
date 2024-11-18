package com.anhembi.demo.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.anhembi.demo.exception.UserException;
import com.anhembi.demo.model.User;
import com.anhembi.demo.model.dto.LoginDTO;
import com.anhembi.demo.model.dto.RegisterDTO;
import com.anhembi.demo.model.dto.UserDTO;
import com.anhembi.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void userRegister(RegisterDTO registerDTO) {
        registerDTO.setPassword(cryptPassword(registerDTO.getPassword()));
        try {
            userRepository.save(registerDTO.toUser());
        }catch (Exception e){
            throw new UserException("Email já cadastrado");
        }
    }

    public ResponseEntity userLogin(LoginDTO login) {
        User userDB = userRepository.findByEmail(login.getEmail());

        if (userDB == null) {
            return ResponseEntity.status(401).build();
        }
        UserDTO userDTO = new UserDTO(userDB);

        return checkPassword(login.getPassword(), userDB.getPassword()) ? ResponseEntity.ok(userDTO) : ResponseEntity.status(401).build();
    }

    private String cryptPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    private boolean checkPassword(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }
}
