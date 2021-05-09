package com.mindtree.entities.product;

import com.mindtree.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy =  InheritanceType.JOINED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue
    private Long productId;

    private String productName;

    private Double price;

}
