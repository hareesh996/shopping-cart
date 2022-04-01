package com.mindtree.web.dto.product;

import com.mindtree.core.sql.SortOrder;

import lombok.Data;

@Data
public class ProductSearchDto {
    private int name;

    private int pageNumber;
    private int totalCount;
    private int pageSize;

    private SortRecordsBy sortRecordBy;
    private SortOrder sortOrder;

}
