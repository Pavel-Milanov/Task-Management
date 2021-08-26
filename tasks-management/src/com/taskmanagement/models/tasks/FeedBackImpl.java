package com.taskmanagement.models.tasks;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.models.ActivityHistoryImpl;
import com.taskmanagement.models.contracts.FeedBack;
import com.taskmanagement.models.enums.FeedBackStatus;

import java.time.LocalDateTime;

public class FeedBackImpl extends WorkingItemImpl implements FeedBack {

    private int rating;
    private FeedBackStatus feedBackStatus;

    public FeedBackImpl(int id, String title, String description, int rating, FeedBackStatus feedBackStatus) {
        super(id, title, description);
        setRating(rating);
        setFeedBackStatus(feedBackStatus);
    }

    @Override
    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public FeedBackStatus getFeedBackStatus() {
        return feedBackStatus;
    }

    private void setFeedBackStatus(FeedBackStatus feedBackStatus) {
        this.feedBackStatus = feedBackStatus;
    }

    @Override
    public void changeFeedbackRating(int rating) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.FEEDBACK_RATING_CHANGED, getRating(), rating), LocalDateTime.now()));
        setRating(rating);
    }

    @Override
    public void changeFeedbackStatus(FeedBackStatus feedBackStatus) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.FEEDBACK_STATUS_CHANGED, getFeedBackStatus(), feedBackStatus), LocalDateTime.now()));
        setFeedBackStatus(feedBackStatus);
    }

    @Override
    public String getAsString() {
        return "Feedback : " + super.getAsString() + " : " +
                ", Status " + getFeedBackStatus() + ", Rating " + getRating();
    }
}
