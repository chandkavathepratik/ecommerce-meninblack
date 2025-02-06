package com.meninblack.controllers.usercontrollers;

import com.meninblack.entities.sub.Address;
import com.meninblack.service.userservice.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping
    public ResponseEntity<String> addAddress(@RequestBody Address address){
        try {
            return new ResponseEntity<>(userAddressService.addAddress(address),  HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddress(){
        try {
            List<Address> add = userAddressService.getAddress();
            return new ResponseEntity<>(add, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/addId")
    public ResponseEntity<Address> getAddress(@PathVariable String addId){
        try {
            return new ResponseEntity<>(userAddressService.getAddress(addId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{addId}")
    public ResponseEntity<String> updateAddress(@PathVariable String addId, @RequestBody Address address){
        try {
            userAddressService.updateAddress(addId, address);
            return new ResponseEntity<>("Address updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{addId}")
    public ResponseEntity<String> deleteAddress(@PathVariable String addId){
        try {
            userAddressService.deleteAddress(addId);
            return new ResponseEntity<>("Address deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
