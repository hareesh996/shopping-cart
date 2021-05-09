package com.mindtree.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchDto extends ProductSearchDto {
    private String genre;
    private String author;
}
