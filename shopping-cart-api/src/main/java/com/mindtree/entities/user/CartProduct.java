package com.mindtree.entities.user;

import com.mindtree.entities.BaseEntity;
import com.mindtree.entities.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class CartProduct extends BaseEntity {

    @Id
    @SequenceGenerator(name = "cart_product_seq", initialValue = 1)
    @GeneratedValue(generator = "cart_product_seq")
    private Long cartProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    private int numberOfQuantities;

}
