package com.meninblack.service;

import com.meninblack.dto.UserDto;
import com.meninblack.entities.User;
import com.meninblack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PublicService {

    @Autowired
    private UserRepository uRepo;

    private final PasswordEncoder passEncoder = new BCryptPasswordEncoder();

    public void createUser(UserDto newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPhone_number(newUser.getPhone_number());
        user.setPassword(passEncoder.encode(newUser.getPassword()));
        user.getRoles().add("User");
        user.setUsername(user.getEmail());
        uRepo.save(user);
    }
}
