package com.taskmanagеment.models.tasks;

import com.taskmanagеment.constants.ModelConstants;
import com.taskmanagеment.models.ActivityHistoryImpl;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Task;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.models.enums.TaskType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskBase implements Bug {

    private final Severity initialSeverity = Severity.CRITICAL;
    private final Severity finalSeverity = Severity.MINOR;
    private  Severity severity;
    private final BugStatus initialStatus = BugStatus.ACTIVE;
    private final BugStatus finalStatus = BugStatus.FIXED;
    private  BugStatus bugStatus;

    private final List<String> stepsToReproduce = new ArrayList<>();


    public BugImpl(int id, String title, String description, Priority priority, Severity severity, BugStatus status, String assignee) {
        super(id, title, description,priority, assignee);
        setSeverity(severity);
        setBugStatus(status);
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
    public void addStepToReproduce(List<String> steps) {
        addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.STEP_ADDED, steps.toString()), LocalDateTime.now()));
        stepsToReproduce.addAll(steps);
    }

  //  @Override
  //  public void removeStepToReproduce(String step) {
  //     addActivityHistory(new ActivityHistoryImpl(String.format(ModelConstants.STEP_REMOVED, step), LocalDateTime.now()));
  //      stepsToReproduce.remove(step);
  //  }

    private void setSeverity(Severity severity) {
        this.severity = severity;
    }

    private void setBugStatus(BugStatus bugStatus) {
        this.bugStatus = bugStatus;
    }

    @Override
    public void changeBugPriority(Priority priority) {
        getActivityHistory().add(new ActivityHistoryImpl(String.format("Priority was changed from %s to %s",getPriority(),priority),LocalDateTime.now()));
        setPriority(priority);
    }

    @Override
    public void changeBugSeverity(Severity severity) {
        getActivityHistory().add(new ActivityHistoryImpl(String.format("Severity was changed from %s to %s",getSeverity(),severity),LocalDateTime.now()));
        setSeverity(severity);
    }

    @Override
    public void changeBugStatus(BugStatus bugStatus) {
        getActivityHistory().add(new ActivityHistoryImpl(String.format("Bug status was changed from %s to %s",getBugStatus(),bugStatus),LocalDateTime.now()));

        setBugStatus(bugStatus);
    }


    @Override
    public TaskType getType() {
        return TaskType.BUG;
    }
}
