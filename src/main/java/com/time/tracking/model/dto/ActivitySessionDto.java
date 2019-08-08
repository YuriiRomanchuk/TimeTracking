package com.time.tracking.model.dto;

import com.time.tracking.model.dto.user.UserDto;

import java.util.Objects;

public class ActivitySessionDto {

    private int id;
    private ActivityDto activity;
    private UserDto userDto;
    private int timeSpent;


    public int getId() {
        return id;
    }

    public ActivityDto getActivityDto() {
        return activity;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActivityDto(ActivityDto activity) {
        this.activity = activity;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitySessionDto that = (ActivitySessionDto) o;
        return id == that.id &&
                timeSpent == that.timeSpent &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(userDto, that.userDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, userDto, timeSpent);
    }
}
