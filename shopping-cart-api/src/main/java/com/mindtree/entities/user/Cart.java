package com.mindtree.entities.user;

import com.mindtree.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table
@NamedEntityGraph(      // This is needed in case we are using the EntityManager to query the products as well along with cart.
        name = "cart.cartProducts",
        attributeNodes = @NamedAttributeNode(
                value = "cartProducts",
                subgraph = "cart.cartProducts.product"
        ),
        subgraphs = @NamedSubgraph(
                name = "cart.cartProducts.product",
                attributeNodes = @NamedAttributeNode("product")
        )

)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts;

}
