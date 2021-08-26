package com.taskmanagement.models.contracts;

import java.util.List;

public interface WorkingItem extends Identifiable, Printable, Commentable {
    String getName();

    String getDescription();

    List<ActivityHistory> getActivityHistory();

    void addComment(Comment comment);

    void removeComment(Comment comment);

}
