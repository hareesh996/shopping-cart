package com.mindtree.services.product;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindtree.core.exception.BusinessException;
import com.mindtree.dao.product.ProductSearchRepository;
import com.mindtree.entities.product.Apparel;
import com.mindtree.entities.product.Book;
import com.mindtree.web.dto.product.ApparelDto;
import com.mindtree.web.dto.product.ApparelSearchDto;
import com.mindtree.web.dto.product.BookDto;
import com.mindtree.web.dto.product.BookSearchDto;
import com.mindtree.web.mappers.ProductDtoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
        if(Objects.isNull(books) || books.isEmpty()) {
        	log.error("We did not find any book for the search criteria [{}]", bookSearchDto);
        	throw new BusinessException("bookSearch.empty", HttpStatus.NOT_FOUND, "We did not find any book for the search criteria");
        }
        if(log.isDebugEnabled()) {
        	log.debug("We found the books for the given search criteria [{}]", bookSearchDto);
        }
        return this.productDtoMapper.bookDto(books);
    }

    @Override
    public List<ApparelDto> searchApparels(ApparelSearchDto apparelSearchDto) {
        List<Apparel> apparels = this.productSearchRepository.searchApparels(apparelSearchDto);
        if(Objects.isNull(apparels) || apparels.isEmpty()) {
        	log.error("We did not find any apparels for the search criteria [{}]", apparelSearchDto);
        	throw new BusinessException("apparelsSearch.empty", HttpStatus.NOT_FOUND, "We did not find any apparels for the search criteria");
        }
        if(log.isDebugEnabled()) {
        	log.debug("We found the apparels for the given search criteria [{}]", apparelSearchDto);
        }
        return this.productDtoMapper.apparelDto(apparels);
    }
}
