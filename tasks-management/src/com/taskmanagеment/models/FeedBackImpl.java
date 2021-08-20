package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.FeedBack;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.enums.FeedBackStatus;

public class FeedBackImpl extends BaseTask implements FeedBack {

    private int rating;
    private FeedBackStatus feedBackStatus;

    public FeedBackImpl(int id, String title, String description, int rating, FeedBackStatus feedBackStatus) {
        super(id, title, description);
        this.rating = rating;
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
    public void addTask(Task task) {
    }

    @Override
    public void removeTask(Task task) {

    }
}
