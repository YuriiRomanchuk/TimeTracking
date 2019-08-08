package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.dtoConverter.ActivitySessionDtoFromEntityConverter;
import com.time.tracking.converter.entityConverter.ActivitySessionConverter;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dao.ActivitySessionDao;
import com.time.tracking.model.dto.ActivitySessionDto;
import com.time.tracking.model.entity.ActivitySession;

@InitializeComponent
public class ActivitySessionService {

    private final ActivitySessionDao activitySessionDao;
    private final ActivitySessionConverter activitySessionConverter;
    private final ActivitySessionDtoFromEntityConverter activitySessionDtoFromEntityConverter;

    public ActivitySessionService(ActivitySessionDao activitySessionDao,
                                  ActivitySessionConverter activitySessionConverter,
                                  ActivitySessionDtoFromEntityConverter activitySessionDtoFromEntityConverter) {
        this.activitySessionDao = activitySessionDao;
        this.activitySessionConverter = activitySessionConverter;
        this.activitySessionDtoFromEntityConverter = activitySessionDtoFromEntityConverter;
    }

    public void addActivitySession(ActivitySessionDto activitySessionDto) throws ServiceException {
        try {
            ActivitySession activitySession = activitySessionConverter.convert(activitySessionDto);
            activitySessionDao.insert(activitySession);
        } catch (Exception e) {
            throw new ServiceException("Activity session create failed", e);
        }
    }
}
