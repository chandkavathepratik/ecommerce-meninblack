package com.meninblack.repositories.impl;

import com.meninblack.entities.Product;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoConverter mongoConverter;

    private MongoDatabase getDatabase() {
        return mongoTemplate.getDb();
    }

    public List<Product> searchByText(String text) {
        List<Product>  products = new ArrayList<>();

        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection("products");

        AggregateIterable<Document> result = collection.aggregate(List.of(new Document("$search",
                new Document("index", "meninblack-products")
                        .append("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("name", "description", "brand"))))));

        result.forEach(doc -> products.add(mongoConverter.read(Product.class, doc)));
        return products;
    }

}
