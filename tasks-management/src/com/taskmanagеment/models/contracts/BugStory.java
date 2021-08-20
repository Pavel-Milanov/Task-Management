package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.Priority;

public interface BugStory {


    Priority getPriority();

    String getAssignee();
}
