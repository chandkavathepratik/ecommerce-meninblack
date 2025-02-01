package com.meninblack.entities.sub;

import com.meninblack.entities.enums.product.Size;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

        @NonNull
        private ObjectId productId;

        @NonNull
        private Integer quantity;

        @NonNull
        private Size size;

        private Double cartItemValue;

}
