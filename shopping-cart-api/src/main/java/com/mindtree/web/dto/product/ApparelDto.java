package com.mindtree.web.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data()
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor()
public class ApparelDto extends ProductDto {
    private String type;
    private String brand;
    private String design;
}
