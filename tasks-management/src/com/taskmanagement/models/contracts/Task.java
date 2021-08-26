package com.taskmanagement.models.contracts;

import com.taskmanagement.models.enums.Priority;

public interface Task extends WorkingItem, Assignable {
    Priority getPriority();

    void changePriority(Priority priority);
}
