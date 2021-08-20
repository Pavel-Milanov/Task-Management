package com.taskmanagеment.models.contracts;

import java.util.List;

public interface Task extends Identifiable, Printable, Commentable {

    String getTitle();

    String getDescription();

    List<ActivityHistory> getActiveHistory();




    void addCommend(Comment comment);

    void removeComment(Comment comment);


}
