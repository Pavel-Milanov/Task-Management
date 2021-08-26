package com.taskmanagement.models.contracts;

import java.util.List;

public interface Board extends Printable, Identifiable {

    void addTask(WorkingItem workingItem);

    void removeTask(WorkingItem workingItem);

    String getName();

    List<WorkingItem> getTasks();

    List<ActivityHistory> getActivityHistory();
}
