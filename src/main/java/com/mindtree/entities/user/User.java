package com.mindtree.entities.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "app_user")
public class User {
	
	@Id
	private Long userId;
	
	@Column(unique = true)
	private String userName;
	
	private String firstName;
	private String lastName;
	private String middleName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id", nullable = true, unique = true)
	private Cart cart;	
}
