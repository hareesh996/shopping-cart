package com.mindtree.entities.product;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "product_id")
public class Apparel extends Product {
    private String type;
    private String brand;
    private String design;

    @Builder(builderMethodName = "apparelBuilder")
    public Apparel(Long productId, String productName, Double price, String type, String brand, String design) {
        super(productId, productName, price);
        this.type = type;
        this.brand = brand;
        this.design = design;
    }
}
