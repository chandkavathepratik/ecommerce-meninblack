package com.meninblack.repositories;

import com.meninblack.entities.Product;
import com.meninblack.entities.enums.product.Availability;
import com.meninblack.entities.enums.product.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    List<Product> findByCategory(Category category);

    List<Product> findByAvailability(Availability availability);
}
