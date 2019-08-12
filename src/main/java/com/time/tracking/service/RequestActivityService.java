package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.dtoConverter.RequestActivityDtoFromEntityConverter;
import com.time.tracking.converter.entityConverter.RequestActivityConverter;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dao.RequestActivityDao;
import com.time.tracking.model.dto.RequestActivityDto;
import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.model.entity.RequestActivity;
import com.time.tracking.model.enums.RequestStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@InitializeComponent
public class RequestActivityService {

    private final RequestActivityDao requestActivityDao;
    private final RequestActivityConverter requestActivityConverter;
    private final RequestActivityDtoFromEntityConverter requestActivityDtoFromEntityConverter;

    public RequestActivityService(RequestActivityDao requestActivityDao,
                                  RequestActivityConverter requestActivityConverter,
                                  RequestActivityDtoFromEntityConverter requestActivityDtoFromEntityConverter) {
        this.requestActivityDao = requestActivityDao;
        this.requestActivityConverter = requestActivityConverter;
        this.requestActivityDtoFromEntityConverter = requestActivityDtoFromEntityConverter;
    }

    public void addRequestActivity(RequestActivityDto requestActivityDto) throws ServiceException {
        try {
            RequestActivity requestActivity = requestActivityConverter.convert(requestActivityDto);
            if (!requestActivityDao.findRequestActivityByStatusByActionByUser(requestActivity).isPresent()) {
                requestActivityDao.insert(requestActivity);
            } else {
                throw new ServiceException("The request have already been distributed for this activity.");
            }
        } catch (Exception e) {
            throw new ServiceException("Request activity create failed", e);
        }
    }

    public List<RequestActivityDto> receiveFreeActivitiesForUser() throws ServiceException {
        try {
            return requestActivityDao.receiveActivityRequestsByStatus(RequestStatus.NEW).stream().map(requestActivityDtoFromEntityConverter::convert).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Requests activity receive failed", e);
        }
    }

    public void changeRequestRoomStatus(int requestActivityId, RequestStatus requestStatus, Date date) throws ServiceException {
        try {
            requestActivityDao.updateRequestActivityStatus(requestActivityId, requestStatus, date);
        } catch (Exception e) {
            throw new ServiceException("Request activity change failed", e);
        }
    }

    public List<RequestActivityDto> receiveActivityRequestsByUserId(UserDto userDto) throws ServiceException {
        try {
            return requestActivityDao.receiveActivityRequestsByUserId(userDto.getId()).stream().map(requestActivityDtoFromEntityConverter::convert).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Requests activity receive failed", e);
        }
    }

}
