package com.meninblack.service.userservice;

import com.meninblack.entities.Product;
import com.meninblack.entities.User;
import com.meninblack.entities.sub.CartItem;
import com.meninblack.repositories.ProductRepository;
import com.meninblack.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductService {

    @Autowired
    private ProductRepository pRepo;

    @Autowired
    private UserRepository uRepo;

    public String addToCart(CartItem item) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        Product product = pRepo.findById(item.getProductId()).orElse(null);
        if(item.getQuantity()>0 && product!=null){
            item.setCartItemValue((double) ((item.getQuantity()) * product.getPrice()));
            user.getCart().add(item);
            uRepo.save(user);
            return "Product added to cart";
        }
        return "Failed to add product to cart";
    }

    public void addToWishlist(String prodId) {
        Product product = pRepo.findById(new ObjectId(prodId)).orElse(null);
        if(product!=null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = uRepo.findByUsername(username);
            user.getWishlist().add(product);
            uRepo.save(user);
        }
    }

    public List<CartItem> getCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = uRepo.findByUsername(auth.getName());
        return user.getCart();
    }

    public List<Product> getWishlist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = uRepo.findByUsername(auth.getName());
        return user.getWishlist();
    }

}
