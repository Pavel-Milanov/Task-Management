package com.taskmanagement.models.contracts;

import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.models.enums.Severity;

import java.util.List;

public interface Bug extends Task {
    Severity getSeverity();

    BugStatus getBugStatus();

    List<String> getStepsToReproduce();

    void addStepToReproduce(List<String> steps);

    void changeBugSeverity(Severity severity);

    void changeBugStatus(BugStatus bugStatus);
}
