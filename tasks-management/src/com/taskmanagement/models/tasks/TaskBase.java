package com.taskmanagement.models.tasks;


import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.models.ActivityHistoryImpl;
import com.taskmanagement.models.contracts.Task;
import com.taskmanagement.models.enums.Priority;

import java.time.LocalDateTime;

public abstract class TaskBase extends WorkingItemImpl implements Task {

    private Priority priority;
    private String assignee;


    protected TaskBase(int id, String title, String description, Priority priority, String assignee) {
        super(id, title, description);
        setPriority(priority);
        setAssignee(assignee);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void changePriority(Priority priority) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.PRIORITY_CHANGED, getPriority(), priority), LocalDateTime.now()));
        setPriority(priority);
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public void changeAssignee(String assignee) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.TASK_ASSIGNEE_CHANGED, this.assignee, assignee), LocalDateTime.now()));
        setAssignee(assignee);
    }
}
