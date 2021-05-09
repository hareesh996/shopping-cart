package com.mindtree.web.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApparelSearchDto extends ProductSearchDto {
    private String type;
    private String brand;
}
