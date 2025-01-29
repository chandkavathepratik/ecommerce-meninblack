package com.meninblack.entities;

import com.meninblack.entities.sub.Address;
import com.meninblack.entities.sub.CartItem;
import com.meninblack.entities.sub.Order;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId userId;

    @NonNull
    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String password;

    @Indexed(unique = true)
    @NonNull
    private Long phone_number;

    @NonNull
    private String name;

    @DBRef
    private List<Product> wishlist = new ArrayList<>();

    private List<CartItem> cart = new ArrayList<>();

    @DBRef
    private List<Order> orders = new ArrayList<>();

    @DBRef
    private List<Address> addresses = new ArrayList<>();

    private Set<String> roles = new HashSet<>();

}