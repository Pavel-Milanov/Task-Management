package com.taskmanagement.models.contracts;

import com.taskmanagement.models.enums.TaskType;

import java.util.List;

public interface WorkingItem extends Identifiable, Printable, Commentable {

    String getName();

    String getDescription();

    List<ActivityHistory> getActivityHistory();

    void addCommend(Comment comment);

    void removeComment(Comment comment);

    void addComment(Comment comment);

    TaskType getType();

}
