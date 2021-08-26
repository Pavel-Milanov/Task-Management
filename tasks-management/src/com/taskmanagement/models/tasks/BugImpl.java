package com.taskmanagement.models.tasks;

import com.taskmanagement.constants.ModelConstants;
import com.taskmanagement.models.ActivityHistoryImpl;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.models.enums.Severity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskBase implements Bug {

    private final List<String> stepsToReproduce = new ArrayList<>();
    private Severity severity;
    private BugStatus bugStatus;


    public BugImpl(int id, String title, String description, Priority priority, Severity severity, BugStatus status, String assignee) {
        super(id, title, description, priority, assignee);
        setSeverity(severity);
        setBugStatus(status);
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    private void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Override
    public BugStatus getBugStatus() {
        return bugStatus;
    }

    private void setBugStatus(BugStatus bugStatus) {
        this.bugStatus = bugStatus;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    @Override
    public void addStepToReproduce(List<String> steps) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.STEP_ADDED, steps.toString()), LocalDateTime.now()));
        stepsToReproduce.addAll(steps);
    }

    @Override
    public void changeBugSeverity(Severity severity) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.BUG_SEVERITY_CHANGED, getSeverity(), severity), LocalDateTime.now()));
        setSeverity(severity);
    }

    @Override
    public void changeBugStatus(BugStatus bugStatus) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.BUG_STATUS_CHANGED, getBugStatus(), bugStatus), LocalDateTime.now()));
        setBugStatus(bugStatus);
    }

    @Override
    public String getAsString() {
        return "Bug      : " + super.getAsString() +
                ", Bug Status " + getBugStatus() + ", Severity " + getSeverity() + ", Priority: " + getPriority() +
                ", Assignee: " + getAssignee();
    }
}
