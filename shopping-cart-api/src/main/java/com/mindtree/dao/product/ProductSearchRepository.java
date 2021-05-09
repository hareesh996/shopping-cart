package com.mindtree.dao.product;

import com.mindtree.entities.product.Apparel;
import com.mindtree.entities.product.Book;
import com.mindtree.web.dto.product.ApparelSearchDto;
import com.mindtree.web.dto.product.BookSearchDto;

import java.util.List;

public interface ProductSearchRepository {
    /**
     * Search the books based on the criteria with pagination results
     * @param bookSearchDto
     * @return
     */
    public List<Book> searchBooks(BookSearchDto bookSearchDto);

    /**
     * Search the apparels based on the criteria with pagination results
     * @param apparelSearchDto
     * @return
     */
    public List<Apparel> searchApparels(ApparelSearchDto apparelSearchDto);
}
