package com.mindtree.web.dto.user;

import com.mindtree.web.dto.product.ProductDto;
import lombok.Data;

@Data
public class CartProductDto {
    private ProductDto product;
    private int numberOfQuantities;
}
