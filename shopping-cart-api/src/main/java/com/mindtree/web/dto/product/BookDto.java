package com.mindtree.web.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto extends ProductDto {
    private String genre;
    private String author;
    private String publications;
}
