package com.time.tracking.model.dao;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.resultSetConverter.ActivitySessionResultSetConverter;
import com.time.tracking.model.entity.ActivitySession;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@InitializeComponent
public class ActivitySessionDao implements GenericDao<ActivitySession> {

    private final DataSource dataSource;
    private final ActivitySessionResultSetConverter activitySessionResultSetConverter;

    public ActivitySessionDao(DataSource dataSource, ActivitySessionResultSetConverter activitySessionResultSetConverter) {
        this.dataSource = dataSource;
        this.activitySessionResultSetConverter = activitySessionResultSetConverter;
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
                    ps.setTimestamp(4, new Timestamp(activitySession.getCurrentDate().getTime()));
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

    public List<ActivitySession> findByIdTodayActivities(int user_id, Date beginOfDay, Date endOfDay) {
        return dataSource.implementQueries(QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("activity.session.find.today.activities"))
                .setParameters(ps -> {
                    ps.setInt(1, user_id);
                    ps.setTimestamp(2, new Timestamp(beginOfDay.getTime()));
                    ps.setTimestamp(3, new Timestamp(endOfDay.getTime()));
                })
                .setConverter(activitySessionResultSetConverter::convert)
                .build());
    }
}
