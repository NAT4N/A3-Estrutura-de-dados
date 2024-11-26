package com.anhembi.A3.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.anhembi.A3.exception.UserException;
import com.anhembi.A3.model.User;
import com.anhembi.A3.model.dto.LoginDTO;
import com.anhembi.A3.model.dto.RegisterDTO;
import com.anhembi.A3.model.dto.ResponseDTO;
import com.anhembi.A3.model.dto.UserDTO;
import com.anhembi.A3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ResponseDTO> userRegister(RegisterDTO registerDTO) {
        registerDTO.setPassword(cryptPassword(registerDTO.getPassword()));
        try {
            userRepository.save(registerDTO.toUser());
            return ResponseEntity.ok(new ResponseDTO("Usuário cadastrado com sucesso", true));
        }catch (Exception e){
            throw new UserException("Email já cadastrado");
        }
    }

    public ResponseEntity userLogin(LoginDTO login) {
        User userDB = userRepository.findByEmail(login.getEmail());

        if (userDB == null) {
            return ResponseEntity.status(401).body(new ResponseDTO("Usuário não encontrado", false));
        }
        UserDTO userDTO = new UserDTO(userDB);

        return checkPassword(login.getPassword(), userDB.getPassword()) ? ResponseEntity.ok(userDTO) : ResponseEntity.status(401).body(new ResponseDTO("Usuário não encontrado", false));
    }

    private String cryptPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    private boolean checkPassword(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }
}
