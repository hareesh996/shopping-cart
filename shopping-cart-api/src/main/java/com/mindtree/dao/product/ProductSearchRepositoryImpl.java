package com.mindtree.dao.product;

import com.google.common.base.Strings;
import com.mindtree.core.sql.QueryBuilder;
import com.mindtree.core.sql.SortOrder;
import com.mindtree.entities.product.Apparel;
import com.mindtree.entities.product.Book;
import com.mindtree.web.dto.product.ApparelSearchDto;
import com.mindtree.web.dto.product.BookSearchDto;
import com.mindtree.web.dto.product.ProductSearchDto;
import com.mindtree.web.dto.product.SortRecordsBy;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    ProductSearchRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Search the books based on the criteria with pagination results
     *
     */
    public List<Book> searchBooks(BookSearchDto bookSearchDto) {
        QueryBuilder queryBuilder = QueryBuilder.instance("select * from book b join product p on p.product_id = b.product_id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        if (!Strings.isNullOrEmpty(bookSearchDto.getAuthor())) {
            queryBuilder.columnFilterWithAND("b.author","author", "=");
            mapSqlParameterSource.addValue("author", bookSearchDto.getAuthor());
        }
        if (!Strings.isNullOrEmpty(bookSearchDto.getGenre())) {
            queryBuilder.columnFilterWithAND("b.genre", "genre", "=");
            mapSqlParameterSource.addValue("genre", bookSearchDto.getGenre());
        }

        String query = this.addPaginationAndOrderQuery(bookSearchDto, queryBuilder);

        return this.namedParameterJdbcTemplate.query(query, mapSqlParameterSource, (ResultSet rs) -> {
            List<Book> searchResults = new ArrayList<>();
            while (rs.next()) {
                Book book = Book.bookBuilder()
                        .author(rs.getString("author"))
                        .genre(rs.getString("genre"))
                        .publications(rs.getString("publications"))
                        .productId(rs.getLong("product_id"))
                        .productName(rs.getString("product_name"))
                        .price(rs.getDouble("price"))
                        .build();
                searchResults.add(book);
            }
            return searchResults;
        });
    }

    /**
     * Add the Pagination and Order query clause for the product search.
     * @param productSearchDto
     * @param queryBuilder
     * @return
     */
    private String addPaginationAndOrderQuery(ProductSearchDto productSearchDto, QueryBuilder queryBuilder) {
        SortRecordsBy sortRecordsBy = !Objects.isNull(productSearchDto.getSortRecordBy()) ? productSearchDto.getSortRecordBy() : SortRecordsBy.PRICE;
        SortOrder sortOrder = !Objects.isNull(productSearchDto.getSortOrder()) ? productSearchDto.getSortOrder() : SortOrder.DESC;
        return queryBuilder.buildWithPagination(sortRecordsBy.getColumnName(), sortOrder,productSearchDto.getPageNumber(), productSearchDto.getPageSize());
    }

    /**
     * Search the apparels based on the criteria with pagination results
     * @param apparelSearchDto
     * @return
     */
    public List<Apparel> searchApparels(ApparelSearchDto apparelSearchDto) {
        QueryBuilder queryBuilder = QueryBuilder.instance("Select * from apparel a join product p on a.product_id = p.product_id");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        if(!Objects.isNull(apparelSearchDto.getType())){
            queryBuilder.columnFilterWithAND("type","type", "=");
            mapSqlParameterSource.addValue("type",apparelSearchDto.getType());
        }
        if(!Objects.isNull(apparelSearchDto.getBrand())){
            queryBuilder.columnFilterWithAND("brand","brand","=");
            mapSqlParameterSource.addValue("brand",apparelSearchDto.getBrand());
        }
        String query = this.addPaginationAndOrderQuery(apparelSearchDto, queryBuilder);
        return this.namedParameterJdbcTemplate.query(query, mapSqlParameterSource, (ResultSet res) ->{
            List<Apparel> apparelsResult = new ArrayList<>();
            while(res.next()){
                Apparel apparel = Apparel.apparelBuilder().brand(res.getString("brand"))
                                .design(res.getString("design"))
                                .type(res.getString("type"))
                                .productId(res.getLong("product_id"))
                                .price(res.getDouble("price"))
                                .productName(res.getString("product_name"))
                                .build();
                apparelsResult.add(apparel);
            }
            return apparelsResult;
        });
    }
}
