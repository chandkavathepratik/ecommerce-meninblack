package com.meninblack.entities.sub;


import com.meninblack.entities.enums.product.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private ObjectId productId;

    private Integer qty;

    private Size size;

}