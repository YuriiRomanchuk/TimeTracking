package com.time.tracking.model.dao;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.resultSetConverter.RequestActivityResultSetConverter;
import com.time.tracking.model.entity.RequestActivity;

import java.sql.Timestamp;
import java.util.List;

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
}
