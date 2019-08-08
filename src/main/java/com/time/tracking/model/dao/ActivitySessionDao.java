package com.time.tracking.model.dao;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.dtoConverter.ActivitySessionDtoFromEntityConverter;
import com.time.tracking.model.entity.ActivitySession;

import java.util.List;

@InitializeComponent
public class ActivitySessionDao implements GenericDao<ActivitySession> {

    private final DataSource dataSource;
    private final ActivitySessionDtoFromEntityConverter activitySessionDtoFromEntityConverter;

    public ActivitySessionDao(DataSource dataSource, ActivitySessionDtoFromEntityConverter activitySessionDtoFromEntityConverter) {
        this.dataSource = dataSource;
        this.activitySessionDtoFromEntityConverter = activitySessionDtoFromEntityConverter;
    }

    @Override
    public void insert(ActivitySession activitySession) {
        QueryData data = QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("activity.session.create"))
                .setEntities(activitySession)
                .setParameters(ps -> {
                    ps.setInt(1, activitySession.getUser().getId());
                    ps.setInt(2, activitySession.getActivity().getId());
                    ps.setInt(3, activitySession.getTimeSpent());
                })
                .setResultProcessor(r -> activitySession.setId(r.getInt(1)))
                .build();

        dataSource.implementQueries(data);
    }

    @Override
    public ActivitySession findById(int id) {
        return null;
    }

    @Override
    public List<ActivitySession> findAll() {
        return null;
    }

    @Override
    public void update(ActivitySession entity) {

    }

    @Override
    public void delete(int id) {

    }
}
