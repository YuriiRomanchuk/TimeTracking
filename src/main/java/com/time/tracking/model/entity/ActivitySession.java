package com.time.tracking.model.entity;

import java.util.Date;
import java.util.Objects;

public class ActivitySession {

    private int id;
    private Activity activity;
    private User user;
    private Date currentDate;
    private int timeSpent;

    public int getId() {
        return id;
    }

    public Activity getActivity() {
        return activity;
    }

    public User getUser() {
        return user;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitySession that = (ActivitySession) o;
        return id == that.id &&
                timeSpent == that.timeSpent &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(user, that.user) &&
                Objects.equals(currentDate, that.currentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, user, currentDate, timeSpent);
    }
}
