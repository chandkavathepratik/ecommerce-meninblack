package com.meninblack.service;

import com.meninblack.entities.User;
import com.meninblack.entities.sub.Address;
import com.meninblack.repositories.AddressRepository;
import com.meninblack.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private AddressRepository addRepo;

    @Transactional
    public String addAddress(Address address) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        user.getAddresses().add(address);
        addRepo.save(address);
        uRepo.save(user);
        return "Address added";
    }

    public List<Address> getAddress() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        return user.getAddresses();
    }

    @Transactional
    public void updateAddress(String addId, Address address) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        Address add = addRepo.findById(new ObjectId(addId)).orElse(null);
        if(user!=null && add!=null && user.getAddresses().contains(add)){
            add.setArea(address.getArea());
            add.setCity(address.getCity());
            add.setType(address.getType());
            add.setState(address.getState());
            add.setZipCode(address.getZipCode());
            addRepo.save(add);
            uRepo.save(user);
        }
    }

    @Transactional
    public void deleteAddress(String addId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        Address add = addRepo.findById(new ObjectId(addId)).orElse(null);

        if(user!=null && add!=null && user.getAddresses().contains(add)){
            addRepo.delete(add);
            user.getAddresses().remove(add);
            uRepo.save(user);
        }
    }

    public Address getAddress(String addId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        Address add = addRepo.findById(new ObjectId(addId)).orElse(null);

        if(user.getAddresses().contains(add)) {
            return add;
        }
        return null;
    }
}
