package com.midtree.core.sql;

public class QueryBuilder {
    private String query;
    private final StringBuilder whereClauase = new StringBuilder();
    private String paginationAndOrderQuery;

    private QueryBuilder(String query){
        this.query = query;
    }

    public static QueryBuilder instance(String query){
        return new QueryBuilder(query);
    }

    public QueryBuilder columnFilterWithAND(String columnName, String parameterName, String operator){
        if(!this.whereClauase.toString().isBlank()){
            this.whereClauase.append(" AND ");
        }
        this.whereClauase.append(columnName).append(" ").append(operator).append(" :").append(parameterName);
        return this;
    }

    public QueryBuilder columnFilterOR(String columnName, String parameterName, String operator){
        if(!this.whereClauase.toString().isBlank()){
            this.whereClauase.append(" OR ");
        }
        this.whereClauase.append(columnName).append(" ").append(operator).append(" :").append(parameterName);
        return this;
    }

    public String build(){
        return this.query + " where " + this.whereClauase.toString().toString();
    }

    public String buildWithPagination(String orderByCol, SortOrder sortOrder, int pageNumber, int pageSize) {
        return this.build() + new StringBuilder(" ORDER BY ").append(orderByCol).append(" LIMIT ")
                .append(pageSize).append(" OFFSET ").append(pageNumber - 1).toString();
    }

    public String buildWithOrderBy(String orderByCol){
        return this.build() + " ORDER BY " + orderByCol;
    }

}
