package com.time.tracking.model.dto;

import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.model.enums.RequestAction;
import com.time.tracking.model.enums.RequestStatus;

import java.util.Date;
import java.util.Objects;

public class RequestActivityDto {

    private int id;
    private UserDto userDto;
    private ActivityDto activityDto;
    private Date dateReview;
    private RequestAction requestAction;
    private RequestStatus requestStatus;

    public int getId() {
        return id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public ActivityDto getActivityDto() {
        return activityDto;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public RequestAction getRequestAction() {
        return requestAction;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setActivityDto(ActivityDto activityDto) {
        this.activityDto = activityDto;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public void setRequestAction(RequestAction requestAction) {
        this.requestAction = requestAction;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestActivityDto that = (RequestActivityDto) o;
        return id == that.id &&
                Objects.equals(userDto, that.userDto) &&
                Objects.equals(activityDto, that.activityDto) &&
                Objects.equals(dateReview, that.dateReview) &&
                requestAction == that.requestAction &&
                requestStatus == that.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userDto, activityDto, dateReview, requestAction, requestStatus);
    }
}