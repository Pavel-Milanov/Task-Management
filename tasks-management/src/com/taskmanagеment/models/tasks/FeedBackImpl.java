package com.taskmanagеment.models.tasks;

import com.taskmanagеment.models.ActivityHistoryImpl;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.FeedBack;
import com.taskmanagеment.models.enums.FeedBackStatus;
import com.taskmanagеment.models.enums.TaskType;

import java.time.LocalDateTime;

public class FeedBackImpl extends WorkingItemImpl implements FeedBack {

    private int rating;
    private FeedBackStatus feedBackStatus;

    public FeedBackImpl(int id, String title, String description, int rating, FeedBackStatus feedBackStatus) {
        super(id, title, description);
        this.rating = rating;
        this.feedBackStatus = feedBackStatus;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setFeedBackStatus(FeedBackStatus feedBackStatus) {
        this.feedBackStatus = feedBackStatus;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public FeedBackStatus getFeedBackStatus() {
        return feedBackStatus;
    }

    @Override
    public void changeFeedbackRating(int rating) {
        getActivityHistory().add(new ActivityHistoryImpl(String.format("Feedback rating was changed from %s to %s", getRating(), rating), LocalDateTime.now()));

        setRating(rating);
    }

    @Override
    public void changeFeedbackStatus(FeedBackStatus feedBackStatus) {

            getActivityHistory().add(new ActivityHistoryImpl(String.format("Feedback status was changed from %s to %s", getFeedBackStatus(), feedBackStatus), LocalDateTime.now()));
            setFeedBackStatus(feedBackStatus);
        }


    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public TaskType getType() {
        return TaskType.FEEDBACK;
    }
}
