package com.taskmanagement.models.tasks;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.models.ActivityHistoryImpl;
import com.taskmanagement.models.contracts.ActivityHistory;
import com.taskmanagement.models.contracts.Comment;
import com.taskmanagement.models.contracts.WorkingItem;
import com.taskmanagement.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.taskmanagement.constants.ModelConstants.*;
import static com.taskmanagement.constants.OutputMessages.DESCRIPTION_ERR;
import static com.taskmanagement.constants.OutputMessages.TITLE_ERR;

public abstract class WorkingItemImpl implements WorkingItem {

    private final List<Comment> comments = new ArrayList<>();
    private final List<ActivityHistory> activeHistory = new ArrayList<>();
    private final int id;
    private String name;
    private String description;


    public WorkingItemImpl(int id, String name, String description) {
        this.id = id;
        setName(name);
        setDescription(description);

        activeHistory.add(new ActivityHistoryImpl(String.format(ModelConstants.CREATED_ITEM,name), LocalDateTime.now()));

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
    public List<ActivityHistory> getActivityHistory() {
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


    public void addActivityHistory(ActivityHistory activityHistory){
        this.activeHistory.add(activityHistory);
    }
    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }



    @Override
    public String getAsString() {
        return null;
    }


}
