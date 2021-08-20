package com.taskmanagеment.models.tasks;

import com.taskmanagеment.constants.ModelConstants;
import com.taskmanagеment.models.ActivityHistoryImpl;
import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.constants.ModelConstants.*;
import static com.taskmanagеment.constants.OutputMessages.DESCRIPTION_ERR;
import static com.taskmanagеment.constants.OutputMessages.TITLE_ERR;

public abstract class BaseTask implements Task {

    private final List<Comment> comments = new ArrayList<>();
    private final List<ActivityHistory> activeHistory = new ArrayList<>();
    private final int id;
    private String name;
    private String description;


    public BaseTask(int id, String name, String description) {
        this.id = id;
        setName(name);
        setDescription(description);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateInRange(name.length(), TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_ERR);
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateInRange(description.length(), DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, DESCRIPTION_ERR);
        this.description = description;
    }

    @Override
    public List<ActivityHistory> getActiveHistory() {
        return new ArrayList<>(activeHistory);
    }


    @Override
    public void addCommend(Comment comment) {

        comments.add(comment);

        activeHistory.add(new ActivityHistoryImpl(String.format(ModelConstants.COMMENT_ADD_SUCCESSFULLY, comment.getContent()), LocalDateTime.now()));
    }

    @Override
    public void removeComment(Comment comment) {

        comments.remove(comment);

        activeHistory.add(new ActivityHistoryImpl(String.format(ModelConstants.COMMENT_REMOVED_SUCCESSFULLY, comment.getContent()), LocalDateTime.now()));
    }


    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public String getAsString() {
        return null;
    }


    protected void addActiveHistory(ActivityHistoryImpl activityHistory) {
        activeHistory.add(activityHistory);
    }
}
