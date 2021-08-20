package com.taskmanagеment.models;

import com.taskmanagеment.models.BaseTask;
import com.taskmanagеment.models.contracts.BugStory;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.enums.Priority;

public class BaseBugStory extends BaseTask implements BugStory {

    private final Priority priority;
    private final String assignee;


    protected BaseBugStory(int id, String title, String description, Priority priority, String assignee) {
        super(id, title, description);
        this.priority = priority;
        this.assignee = assignee;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }


}
