package com.taskmanagеment.models.tasks;


import com.taskmanagеment.models.ActivityHistoryImpl;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.TaskType;

import java.time.LocalDateTime;

import static com.taskmanagеment.constants.CommandConstants.ASSIGNEE_CHANGED;

public abstract class TaskBase extends WorkingItemImpl implements Task {

    private final Priority initialPriority = Priority.HIGH;
    private final Priority finalPriority = Priority.LOW;

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

    protected void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public void changeAssignee(String assignee) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ASSIGNEE_CHANGED, this.assignee, assignee), LocalDateTime.now()));
        setAssignee(assignee);
    }


    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public abstract TaskType getType();
}
