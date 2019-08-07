package com.time.tracking.model.dao;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.resultSetConverter.UserResultSetConverter;
import com.time.tracking.model.entity.User;

import java.util.List;
import java.util.Optional;

@InitializeComponent
public class UserDao implements GenericDao<User> {

    private final DataSource dataSource;
    private final UserResultSetConverter userResultSetConverter;

    public UserDao(DataSource dataSource, UserResultSetConverter userResultSetConverter) {
        this.dataSource = dataSource;
        this.userResultSetConverter = userResultSetConverter;
    }

    @Override
    public void insert(User entity) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return dataSource.implementQueries(
                QueryData.newBuilder()
                        .setQuery(dataSource.receiveQueryText("user.find.all"))
                        .setConverter(userResultSetConverter::convert)
                        .build());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        List<User> users = dataSource.implementQueries(QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("user.find.by.email.and.password"))
                .setConverter(userResultSetConverter::convert)
                .setParameters(ps -> {
                    ps.setString(1, email);
                    ps.setString(2, password);
                })
                .build());

        return dataSource.receiveFirstRecord(users);
    }

    public void createUser(User user) {
        QueryData data = QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("user.create"))
                .setEntities(user)
                .setParameters(ps -> {
                    ps.setString(1, user.getFirstName());
                    ps.setString(2, user.getLastName());
                    ps.setString(3, user.getMiddleName());
                    ps.setString(4, user.getLogin());
                    ps.setString(5, user.getPassword());
                    ps.setString(6, user.getEmail());
                    ps.setString(7, user.getPhone());
                    ps.setString(8, String.valueOf(user.getRole()));
                })
                .setResultProcessor(r -> user.setId(r.getInt(1)))
                .build();

        dataSource.implementQueries(data);
    }
}
