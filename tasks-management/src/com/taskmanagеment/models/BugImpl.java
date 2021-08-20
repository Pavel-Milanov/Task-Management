package com.taskmanagеment.models;

import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;


import java.util.ArrayList;
import java.util.List;

public class BugImpl extends BaseBugStory implements Bug {


    private final Severity severity;
    private final BugStatus bugStatus;

    private final List<String> stepsToReproduce;

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, BugStatus bugStatus, String assignee) {
        super(id, title, description, priority, assignee);
        this.severity = severity;
        this.bugStatus = bugStatus;
        this.stepsToReproduce = new ArrayList<>();

    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public BugStatus getBugStatus() {
        return bugStatus;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }


    @Override
    public void addStepToReproduce(String step) {

        stepsToReproduce.add(step);
    }

    @Override
    public void removeStepToReproduce(String step) {
        stepsToReproduce.remove(step);
    }


}
