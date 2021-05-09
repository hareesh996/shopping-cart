package com.mindtree.web.mappers;

import com.mindtree.entities.product.Apparel;
import com.mindtree.entities.product.Book;
import com.mindtree.entities.product.Product;
import com.mindtree.web.dto.product.ApparelDto;
import com.mindtree.web.dto.product.BookDto;
import com.mindtree.web.dto.product.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductDtoMapper {

    List<BookDto> bookDto(List<Book> books);

    List<ApparelDto> apparelDto(List<Apparel> apparels);

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);
}
