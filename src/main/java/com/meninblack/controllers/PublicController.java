package com.meninblack.controllers;

import com.meninblack.dto.UserDto;
import com.meninblack.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class PublicController {

    @Autowired
    private PublicService publicService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDto newUser) {
        try{
            publicService.createUser(newUser);
            return new ResponseEntity<>("User created successfully..!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public RedirectView redirectToSwagger(){
        return new RedirectView("/swagger-ui/index.html");
    }
}
