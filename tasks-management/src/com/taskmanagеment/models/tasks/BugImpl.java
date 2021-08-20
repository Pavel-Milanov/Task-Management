package com.taskmanagеment.models.tasks;

import com.taskmanagеment.constants.ModelConstants;
import com.taskmanagеment.models.ActivityHistoryImpl;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.BugStory;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.models.enums.TaskType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BugImpl extends BaseBugStory implements Bug, BugStory {

    private final Severity initialSeverity = Severity.CRITICAL;
    private final Severity finalSeverity = Severity.MINOR;
    private final Severity severity;
    private final BugStatus initialStatus = BugStatus.ACTIVE;
    private final BugStatus finalStatus = BugStatus.FIXED;
    private final BugStatus bugStatus;

    private final List<String> stepsToReproduce = new ArrayList<>();


    public BugImpl(int id, String title, String description, String assignee) {
        super(id, title, description, assignee);
        severity = initialSeverity;
        bugStatus = initialStatus;
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
        addActiveHistory(new ActivityHistoryImpl(String.format(ModelConstants.STEP_ADDED, step), LocalDateTime.now()));
        stepsToReproduce.add(step);
    }

    @Override
    public void removeStepToReproduce(String step) {
        addActiveHistory(new ActivityHistoryImpl(String.format(ModelConstants.STEP_REMOVED, step), LocalDateTime.now()));
        stepsToReproduce.remove(step);
    }


    @Override
    public TaskType getType() {
        return TaskType.BUG;
    }
}
