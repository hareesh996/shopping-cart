package com.mindtree.entities.user;

import com.mindtree.entities.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	
	@OneToOne(optional = false, mappedBy = "cart")
	private User user;
	
	@OneToMany()
	@JoinColumn(name="productId")
	private List<Product> products;
}
