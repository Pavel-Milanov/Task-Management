package com.taskmanagеment.models.contracts;

import java.util.List;

public interface Board extends Printable,Identifiable {


    void addTask(WorkingItem workingItem);

    void removeTask(WorkingItem workingItem);

    void addActivityHistory(ActivityHistory activityHistory);

    void removeActivityHistory(ActivityHistory activityHistory);

    String getName();

    List<WorkingItem> getTasks();

    List<ActivityHistory> getActivityHistory();
}
