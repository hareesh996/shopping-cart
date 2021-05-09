package com.mindtree.services.product;

import com.mindtree.dao.product.ProductSearchRepository;
import com.mindtree.dao.product.ProductSearchRepositoryImpl;
import com.mindtree.entities.product.Apparel;
import com.mindtree.web.dto.product.ApparelDto;
import com.mindtree.web.dto.product.ApparelSearchDto;
import com.mindtree.web.dto.product.BookDto;
import com.mindtree.web.dto.product.BookSearchDto;
import com.mindtree.entities.product.Book;
import com.mindtree.web.mappers.ProductDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductSearchRepository productSearchRepository;
    private ProductDtoMapper productDtoMapper;

    public ProductServiceImpl(@Qualifier(value = "productSearchByHibernate") ProductSearchRepository productSearchRepository, ProductDtoMapper productDtoMapper) {
        this.productSearchRepository = productSearchRepository;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public List<BookDto> searchBooks(BookSearchDto bookSearchDto) {
        List<Book> books = this.productSearchRepository.searchBooks(bookSearchDto);
        return this.productDtoMapper.bookDto(books);
    }

    @Override
    public List<ApparelDto> searchApparels(ApparelSearchDto apparelSearchDto) {
        List<Apparel> apparels = this.productSearchRepository.searchApparels(apparelSearchDto);
        return this.productDtoMapper.apparelDto(apparels);
    }
}
