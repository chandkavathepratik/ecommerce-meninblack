package com.meninblack.controllers.usercontrollers;

import com.meninblack.dto.UserDto;
import com.meninblack.dto.UserSignupDto;
import com.meninblack.service.userservice.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping
    public ResponseEntity<UserDto> getUserDetails(){
        try {
            return new ResponseEntity<>(userAccountService.getUserDetails(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserSignupDto userDetails){
        try {
            userAccountService.updateUser(userDetails);
            return new ResponseEntity<>("User details updated.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
