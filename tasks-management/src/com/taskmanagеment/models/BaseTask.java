package com.taskmanagеment.models;

import com.taskmanagеment.Constants.ModelConstants;
import com.taskmanagеment.Constants.OutputMessages;
import com.taskmanagеment.models.contracts.ActivityHistory;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.utils.ValidationHelpers;


import java.util.ArrayList;
import java.util.List;

import static com.taskmanagеment.Constants.ModelConstants.*;
import static com.taskmanagеment.Constants.OutputMessages.*;

public abstract class BaseTask implements Task {

    private int id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<ActivityHistory> activeHistory;


    public BaseTask(int id, String title, String description) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.activeHistory = new ArrayList<>();
    }


    private void setTitle(String title) {
        ValidationHelpers.validateInRange(title.length(),TITLE_MIN_LENGTH,TITLE_MAX_LENGTH,TITLE_ERR);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateInRange(description.length(),DESCRIPTION_MIN_LENGTH,DESCRIPTION_MAX_LENGTH, DESCRIPTION_ERR);
        this.description = description;
    }


    @Override
    public int getId() {
        return id;
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<ActivityHistory> getActiveHistory() {
        return new ArrayList<>(activeHistory);
    }





    @Override
    public void addCommend(Comment comment) {

        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {


        if (comments.isEmpty()){
            throw new IllegalArgumentException("There is no comments");
        }
        comments.remove(comment);
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
