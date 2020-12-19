package com.mindtree.entities.product;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
	
	@Id
	@GeneratedValue
	Long productId;
	
	String productName;
	Float price;
	
	@CreatedDate
	LocalDateTime createdDate;
	
	@LastModifiedDate
	LocalDateTime modifiedDate;
}
