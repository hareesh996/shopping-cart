package com.mindtree.entities.product;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "product_id")
public class Book extends Product {
    private String genre;
    private String author;
    private String publications;

    @Builder(builderMethodName = "bookBuilder")
    public Book(Long productId, String productName, Double price, String genre, String author, String publications) {
        super(productId, productName, price);
        this.genre = genre;
        this.author = author;
        this.publications = publications;
    }
}
