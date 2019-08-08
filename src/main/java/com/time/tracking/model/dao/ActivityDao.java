package com.time.tracking.model.dao;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.resultSetConverter.ActivityResultSetConverter;
import com.time.tracking.model.entity.Activity;
import com.time.tracking.model.enums.RequestStatus;

import java.util.List;

@InitializeComponent
public class ActivityDao implements GenericDao<Activity> {

    private final DataSource dataSource;
    private final ActivityResultSetConverter activityResultSetConverter;

    public ActivityDao(DataSource dataSource, ActivityResultSetConverter activityResultSetConverter) {
        this.dataSource = dataSource;
        this.activityResultSetConverter = activityResultSetConverter;
    }

    @Override
    public void insert(Activity activity) {
        QueryData data = QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("activity.create"))
                .setEntities(activity)
                .setParameters(ps -> {
                    ps.setString(1, activity.getName());
                })
                .setResultProcessor(r -> activity.setId(r.getInt(1)))
                .build();

        dataSource.implementQueries(data);
    }

    @Override
    public Activity findById(int id) {
        return null;
    }

    @Override
    public List<Activity> findAll() {
        return dataSource.implementQueries(
                QueryData.newBuilder()
                        .setQuery(dataSource.receiveQueryText("activity.find.all"))
                        .setConverter(activityResultSetConverter::convert)
                        .build());
    }

    @Override
    public void update(Activity entity) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Activity> receiveFreeActivitiesForUser(int userId) {
        return dataSource.implementQueries(
                QueryData.newBuilder()
                        .setQuery(dataSource.receiveQueryText("activity.find.free"))
                        .setParameters(ps -> {
                            ps.setInt(1, userId);
                        })
                        .setConverter(activityResultSetConverter::convert)
                        .build());
    }
}

