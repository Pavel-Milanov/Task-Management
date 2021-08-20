package com.taskmanagеment.models.tasks;

import com.taskmanagеment.constants.ModelConstants;
import com.taskmanagеment.models.ActivityHistoryImpl;
import com.taskmanagеment.models.contracts.BugStory;
import com.taskmanagеment.models.contracts.Comment;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.TaskType;

import java.time.LocalDateTime;

public abstract class BaseBugStory extends BaseTask implements BugStory {

    private final Priority initialPriority = Priority.HIGH;
    private final Priority finalPriority = Priority.LOW;
    private final Priority priority;
    private String assignee;


    protected BaseBugStory(int id, String title, String description, String assignee) {
        super(id, title, description);
        setAssignee(assignee);
        priority = initialPriority;
    }

    @Override
    public Priority getPriority() {
        return priority;
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
        addActiveHistory(new ActivityHistoryImpl(String.format(ModelConstants.ASSIGNEE_CHANGED, this.assignee, assignee), LocalDateTime.now()));
        setAssignee(assignee);
    }


    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public abstract TaskType getType();
}
