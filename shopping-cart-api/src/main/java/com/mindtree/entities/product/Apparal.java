package com.mindtree.entities.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Apparal extends Product{
	private String type;
	private String brand;
	private String design;
}