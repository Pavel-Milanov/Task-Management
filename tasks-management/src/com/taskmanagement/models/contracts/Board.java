package com.taskmanagement.models.contracts;

import java.util.List;

public interface Board extends Printable, Identifiable {

    void addWorkingItem(WorkingItem workingItem);

    void removeWorkingItem(WorkingItem workingItem);

    String getName();

    List<WorkingItem> getWorkingItems();

    List<ActivityHistory> getActivityHistory();
}
