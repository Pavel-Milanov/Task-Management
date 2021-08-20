package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.TaskType;

import java.util.List;

public interface Task extends Identifiable, Printable, Commentable {

    String getName();

    String getDescription();

    List<ActivityHistory> getActiveHistory();

    void addCommend(Comment comment);

    void removeComment(Comment comment);

    void addComment(Comment comment);

    TaskType getType();

}
