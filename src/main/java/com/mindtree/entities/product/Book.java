package com.mindtree.entities.product;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Book extends Product{
	private String genre;
	private String authour;
	private String publications;

}
