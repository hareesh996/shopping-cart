package com.mindtree.entities.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
