package com.meninblack.entities.sub;

import com.meninblack.entities.enums.address.AddressType;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private ObjectId addId;

    @NonNull
    private AddressType type;

    @NonNull
    private String area;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @NonNull
    private Integer zipCode;

}
