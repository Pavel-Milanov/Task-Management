package com.taskmanagеment.models.contracts;

import java.util.List;

public interface BaseModel extends Printable {

    String getName();
    void addTask(Task task);

    void removeTask(Task task);

    void addActivityHistory(ActivityHistory activityHistory);

    void removeActivityHistory(ActivityHistory activityHistory);
    List<Task> getTasks();
    List<ActivityHistory> getActiveHistory();

}
