package com.meninblack.service.publicservice;

import com.meninblack.entities.Product;
import com.meninblack.entities.enums.product.Availability;
import com.meninblack.entities.enums.product.Category;
import com.meninblack.repositories.ProductRepository;
import com.meninblack.repositories.impl.ProductRepoImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicProductService {

    @Autowired
    private ProductRepository pRepo;

    @Autowired
    private ProductRepoImpl pRepoImpl;

    public List<Product> getAllProducts() {
        return pRepo.findAll();
    }


    public Product getProduct(String prodId) {
        ObjectId id = new ObjectId(prodId);
        return pRepo.findById(id).orElse(null);
    }

    public List<Product> findByCategory(Category category) {
        return pRepo.findByCategory(category);
    }

    public List<Product> findByAvailability(Availability availability) {
        return pRepo.findByAvailability(availability);
    }

    public List<Product> search(String text) {
        return pRepoImpl.searchByText(text);
    }
}
