package com.time.tracking.model.dao;

import com.time.tracking.util.FileReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

public class DataSource {

    private final String url;
    private final String userName;
    private final String password;
    private final Properties properties;
    private final Properties queries;

    public DataSource() {

        //TODO: refactoring
        properties = FileReader.receiveResourceProperties("sql/base_connection.properties");
        queries = FileReader.receiveResourceProperties("sql/queries.properties");

        testConnection();

        url = properties.getProperty("base.url");
        userName = properties.getProperty("base.userName");
        password = properties.getProperty("base.password");

        String initBdScript = FileReader.receiveResourceString(properties.getProperty("base.init"));
        implementQueries(QueryData.newBuilder().setQuery(initBdScript).build());
    }

    public String receiveQueries(String propertyName) {
        return queries.getProperty(propertyName);
    }

    private Connection receiveConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    private void testConnection() {
        try {
            Class.forName(properties.getProperty("base.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> implementQueries(List<QueryData> queriesData) {

        List<T> results = new ArrayList<>();
        boolean isTransactions = queriesData.size() > 1;
        try (Connection connection = receiveConnection()) {
            if (isTransactions) {
                connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                connection.setAutoCommit(false);
            }
            for (QueryData queryData : queriesData) {
                results.addAll(execute(connection, queryData));
            }
            if (isTransactions) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public <T> List<T> implementQueries(QueryData queryData) {
        List<QueryData> queriesData = new ArrayList<>();
        queriesData.add(queryData);
        return implementQueries(queriesData);
    }

    private <T> List<T> execute(Connection connection, QueryData queryData) throws SQLException {
        boolean isBatch = queryData.getEntities().size() > 1;
        List<T> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(queryData.getQuery(), Statement.RETURN_GENERATED_KEYS)) {
            if (queryData.getParameters() != null) {
                for (Object entity : queryData.getEntities()) {
                    queryData.getParameters().accept(entity, ps);
                    if (isBatch) {
                        ps.addBatch();
                    }
                }
            }

            if (queryData.getQuery().toUpperCase().startsWith("SELECT")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add((T) queryData.getConverter().apply(rs));
                    }
                }
            } else {
                if (isBatch) {
                    ps.executeBatch();
                } else {
                    ps.executeUpdate();
                }
                if (queryData.getResultProcessor() != null) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        while (rs.next()) {
                            queryData.getResultProcessor().accept(rs);
                        }
                    }
                }
            }
        }
        return list;
    }

    public <T> Optional<T> receiveFirstRecord(List<T> results) {
        return results.stream().findFirst();
    }

    interface SqlFunction<ResultSet, T> extends Function<ResultSet, T> {
        @Override
        default T apply(ResultSet resultSet) {
            try {
                return safeApply(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        T safeApply(ResultSet resultSet) throws SQLException;
    }

    interface SqlConsumer<T> extends Consumer<T> {
        @Override
        default void accept(T preparedStatement) {
            try {
                safeApply(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        void safeApply(T preparedStatement) throws SQLException;
    }

}
