package com.taskmanagеment.models.tasks;

import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.FeedBack;
import com.taskmanagеment.models.enums.FeedBackStatus;
import com.taskmanagеment.models.enums.TaskType;

public class FeedBackImpl extends BaseTask implements FeedBack {

    private int rating;
    private FeedBackStatus feedBackStatus;

    public FeedBackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        this.rating = rating;
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
    public void addComment(Comment comment) {

    }

    @Override
    public TaskType getType() {
        return TaskType.FEEDBACK;
    }
}
