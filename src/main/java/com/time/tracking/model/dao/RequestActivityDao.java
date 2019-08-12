package com.time.tracking.model.dao;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.resultSetConverter.RequestActivityResultSetConverter;
import com.time.tracking.model.entity.RequestActivity;
import com.time.tracking.model.enums.RequestStatus;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@InitializeComponent
public class RequestActivityDao implements GenericDao<RequestActivity> {

    private final DataSource dataSource;
    private final RequestActivityResultSetConverter requestActivityResultSetConverter;

    public RequestActivityDao(DataSource dataSource, RequestActivityResultSetConverter requestActivityResultSetConverter) {
        this.dataSource = dataSource;
        this.requestActivityResultSetConverter = requestActivityResultSetConverter;
    }

    @Override
    public void insert(RequestActivity requestActivity) {
        QueryData data = QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("request.activity.create"))
                .setEntities(requestActivity)
                .setParameters(ps -> {
                    ps.setInt(1, requestActivity.getUser().getId());
                    ps.setInt(2, requestActivity.getActivity().getId());
                    ps.setString(3, requestActivity.getRequestAction().toString());
                    ps.setString(4, requestActivity.getRequestStatus().toString());
                    ps.setTimestamp(5, new Timestamp(requestActivity.getDateReview().getTime()));
                })
                .setResultProcessor(r -> requestActivity.setId(r.getInt(1)))
                .build();

        dataSource.implementQueries(data);
    }

    @Override
    public RequestActivity findById(int id) {
        return null;
    }

    @Override
    public List<RequestActivity> findAll() {
        return null;
    }

    @Override
    public void update(RequestActivity entity) {

    }

    @Override
    public void delete(int id) {

    }

    public List<RequestActivity> receiveActivityRequestsByStatus(RequestStatus status) {
        return dataSource.implementQueries(QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("request.activity.find.by.status"))
                .setParameters(ps -> {
                    ps.setString(1, status.toString());
                })
                .setConverter(requestActivityResultSetConverter::convert)
                .build());
    }

    public void updateRequestActivityStatus(int requestActivityId, RequestStatus status, Date dateReview) {
        QueryData data = QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("request.activity.update.status"))
                .setParameters(ps -> {
                    ps.setString(1, status.toString());
                    ps.setTimestamp(2, new Timestamp(dateReview.getTime()));
                    ps.setInt(3, requestActivityId);
                })
                .build();

        dataSource.implementQueries(data);
    }

    public List<RequestActivity> receiveActivityRequestsByUserId(int id) {
        return dataSource.implementQueries(QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("request.activity.find.by.user.id"))
                .setParameters(ps -> {
                    ps.setInt(1, id);
                })
                .setConverter(requestActivityResultSetConverter::convert)
                .build());
    }

    public Optional<RequestActivity> findRequestActivityByStatusByActionByUser(RequestActivity requestActivity) {
        List<RequestActivity> requestsActivity = dataSource.implementQueries(QueryData.newBuilder()
                .setQuery(dataSource.receiveQueryText("request.activity.find.by.user.id.by.status.by.action"))
                .setEntities(requestActivity)
                .setParameters(ps -> {
                    ps.setInt(1, requestActivity.getUser().getId());
                    ps.setString(2, requestActivity.getRequestAction().toString());
                    ps.setString(3, requestActivity.getRequestStatus().toString());
                    ps.setInt(4, requestActivity.getActivity().getId());
                })
                .setConverter(requestActivityResultSetConverter::convert)
                .build());

        return dataSource.receiveFirstRecord(requestsActivity);
    }
}
