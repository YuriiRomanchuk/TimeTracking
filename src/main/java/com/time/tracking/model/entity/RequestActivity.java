package com.time.tracking.model.entity;

import com.time.tracking.model.enums.RequestAction;
import com.time.tracking.model.enums.RequestStatus;

import java.util.Date;
import java.util.Objects;

public class RequestActivity {

    private int id;
    private User user;
    private Activity activity;
    private Date dateReview;
    private RequestAction requestAction;
    private RequestStatus requestStatus;


    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Activity getActivity() {
        return activity;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
        RequestActivity that = (RequestActivity) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(dateReview, that.dateReview) &&
                requestAction == that.requestAction &&
                requestStatus == that.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, activity, dateReview, requestAction, requestStatus);
    }
}
