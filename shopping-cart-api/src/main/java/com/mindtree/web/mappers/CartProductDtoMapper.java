package com.mindtree.web.mappers;

import com.mindtree.entities.user.Cart;
import com.mindtree.web.dto.user.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CartProductDtoMapper {

    CartDto convertToCartDto(Cart cart);

}
