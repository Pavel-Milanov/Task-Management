package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.Priority;

public interface Task extends WorkingItem,Assignable {
    Priority getPriority();

    String getAssignee();

    void changeAssignee(String assignee);


}
