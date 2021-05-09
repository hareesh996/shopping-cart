package com.mindtree.web.dto.product;

public enum SortRecordsBy {
    ID("product_id"), PRICE("price"), NAME("product_name");

    private String columnName;

    SortRecordsBy(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
