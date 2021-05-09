package com.mindtree.services.product;

import com.mindtree.web.dto.product.ApparelDto;
import com.mindtree.web.dto.product.ApparelSearchDto;
import com.mindtree.web.dto.product.BookDto;
import com.mindtree.web.dto.product.BookSearchDto;

import java.util.List;

public interface ProductService {

    /**
     * Search the books based on the search criteria provided.
     *
     * @param bookSearchDto: Search Criteria
     * @return: List of Book Search Results
     */
    public List<BookDto> searchBooks(BookSearchDto bookSearchDto);

    /**
     * Search the Apparels based on the search criteria provided.
     *
     * @param apparelSearchDto
     * @return
     */
    public List<ApparelDto> searchApparels(ApparelSearchDto apparelSearchDto);

}
