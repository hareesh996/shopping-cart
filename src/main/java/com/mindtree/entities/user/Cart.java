package com.mindtree.entities.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.mindtree.entities.product.Product;

import lombok.Data;

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
