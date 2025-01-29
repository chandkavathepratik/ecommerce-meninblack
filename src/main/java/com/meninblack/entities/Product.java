package com.meninblack.entities;

import com.meninblack.entities.enums.product.*;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private ObjectId productId;

    @NonNull
    private String name;

    @NonNull
    private String brand;

    @NonNull
    private String description;

    @NonNull
    private Float price;

    @NonNull
    private Color color;

    @NonNull
    private List<Size> availableSizes = new ArrayList<>();

    @NonNull
    private Category category;

    @NonNull
    private Availability availability;

    @NonNull
    private Fabric fabric;

    @NonNull
    private Pattern pattern;

    @NonNull
    private SleeveLength sleeveLength;

    @NonNull
    private Neck neck;

    @NonNull
    private Fit fit;

    @NonNull
    private Closure closure;

    @NonNull
    private List<String> images = new ArrayList<>();

}