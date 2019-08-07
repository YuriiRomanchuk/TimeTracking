package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.entityConverter.RequestActivityConverter;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dao.RequestActivityDao;
import com.time.tracking.model.dto.RequestActivityDto;
import com.time.tracking.model.entity.RequestActivity;

@InitializeComponent
public class RequestActivityService {

    private final RequestActivityDao requestActivityDao;
    private final RequestActivityConverter requestActivityConverter;

    public RequestActivityService(RequestActivityDao requestActivityDao, RequestActivityConverter requestActivityConverter) {
        this.requestActivityDao = requestActivityDao;
        this.requestActivityConverter = requestActivityConverter;
    }

    public void addRequestActivity(RequestActivityDto requestActivityDto) throws ServiceException {
        try {
            RequestActivity requestActivity = requestActivityConverter.convert(requestActivityDto);
            requestActivityDao.insert(requestActivity);
        } catch (Exception e) {
            throw new ServiceException("Activity create failed", e);
        }
    }
}
