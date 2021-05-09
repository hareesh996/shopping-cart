package com.mindtree.web.dto.user;

import com.mindtree.web.dto.product.ProductDto;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private Long cartId;
    private List<CartProductDto> cartProducts;

}
