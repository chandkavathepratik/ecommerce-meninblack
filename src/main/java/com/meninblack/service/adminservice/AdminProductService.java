package com.meninblack.service.adminservice;

import com.meninblack.entities.Product;
import com.meninblack.entities.enums.product.Availability;
import com.meninblack.repositories.ProductRepository;
import com.meninblack.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminProductService {

    @Autowired
    private ProductRepository pRepo;

    @Autowired
    private UserRepository uRepo;

    public void addNewProduct(Product product) {
        pRepo.save(product);
    }

    public void updateProduct(String prodId, Product product) {
        Product old = pRepo.findById(new ObjectId(prodId)).orElse(null);
        if(old!=null){
            old.setAvailability(product.getAvailability());
            old.setName(product.getName());
            old.setBrand(product.getBrand());
            old.setColor(product.getColor());
            old.setCategory(product.getCategory());
            old.setClosure(product.getClosure());
            old.setDescription(product.getDescription());
            old.setFabric(product.getFabric());
            old.setFit(product.getFit());
            old.setImages(product.getImages());
            old.setNeck(product.getNeck());
            old.setPattern(product.getPattern());
            old.setPrice(product.getPrice());
            old.setAvailableSizes(product.getAvailableSizes());
            old.setSleeveLength(product.getSleeveLength());
            pRepo.save(old);
        }
    }

    public void deleteProduct(String prodId) {
        ObjectId id = new ObjectId(prodId);
        pRepo.deleteById(id);
    }

    public void markAsOutOfStock(String prodId) {
        ObjectId id = new ObjectId(prodId);
        Product product = pRepo.findById(id).orElse(null);
        if(product!=null){
            product.setAvailability(Availability.OutOfStock);
            pRepo.save(product);
        }
    }

    public void markAsInStock(String prodId) {
        ObjectId id = new ObjectId(prodId);
        Product product = pRepo.findById(id).orElse(null);
        if(product!=null){
            product.setAvailability(Availability.InStock);
            pRepo.save(product);
        }
    }
}
