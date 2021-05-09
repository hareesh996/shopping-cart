package com.mindtree.web.dto.user;

import com.mindtree.web.dto.product.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartRequest {
    private UserDto user;
    private ProductDto productDto;
    private int numberOfQuantities;
}
