package com.mindtree.web.dto.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {
    private Long productId;
    private String productName;
    private Double price;
}
