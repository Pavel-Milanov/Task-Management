package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.Priority;

public interface BugStory extends Task {
    Priority getPriority();

    String getAssignee();

    void changeAssignee(String assignee);
}
