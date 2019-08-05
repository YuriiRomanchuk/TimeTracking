package com.time.tracking.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class QueryData {

    private List<?> entities = Collections.singletonList(null);
    private String query;
    private DataSource.SqlConsumer<ResultSet> resultProcessor;
    private BiConsumer<Object, PreparedStatement> parameters;
    private DataSource.SqlFunction<ResultSet, ?> converter;

    private QueryData() {
    }

    public String getQuery() {
        return query;
    }

    public BiConsumer<Object, PreparedStatement> getParameters() {
        return parameters;
    }

    public DataSource.SqlConsumer<ResultSet> getResultProcessor() {
        return resultProcessor;
    }

    public List<?> getEntities() {
        return entities;
    }

    public DataSource.SqlFunction<ResultSet, ?> getConverter() {
        return converter;
    }

    public static Builder newBuilder() {
        return new QueryData().new Builder();
    }

    public class Builder {

        public Builder setQuery(String query) {
            QueryData.this.query = query;
            return this;
        }

        public Builder setParameters(DataSource.SqlConsumer<PreparedStatement> parameters) {
            QueryData.this.parameters = (entity, ps) -> parameters.accept(ps);
            return this;
        }

        public Builder setResultProcessor(DataSource.SqlConsumer<ResultSet> resultProcessor) {
            QueryData.this.resultProcessor = resultProcessor;
            return this;
        }

        public Builder setEntities(Object entity) {
            QueryData.this.entities = Arrays.asList(entity);
            return this;
        }

        public Builder setEntities(List<Object> entity) {
            QueryData.this.entities = entity;
            return this;
        }

        public Builder setConverter(DataSource.SqlFunction<ResultSet, ?> converter) {
            QueryData.this.converter = converter;
            return this;
        }

        public QueryData build() {
            return QueryData.this;
        }

    }
}
