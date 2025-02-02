package com.meninblack.service;

import com.meninblack.dto.UserDto;
import com.meninblack.dto.UserSignupDto;
import com.meninblack.entities.User;
import com.meninblack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

    @Autowired
    private UserRepository uRepo;

    private final PasswordEncoder passEncoder = new BCryptPasswordEncoder();

    public UserDto getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        return new UserDto(user.getEmail(), user.getPhoneNumber(), user.getName());
    }

    @Transactional
    public void updateUser(UserSignupDto userDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        user.setUsername(userDetails.getEmail());
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setPassword(passEncoder.encode(userDetails.getPassword()));
        user.setPhoneNumber(userDetails.getPhone_number());
        uRepo.save(user);
    }

}
