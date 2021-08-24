package com.taskmanagеment.models.contracts;

import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.models.enums.Severity;

import java.util.List;

public interface Bug extends Task {


    Severity getSeverity();

    BugStatus getBugStatus();

    List<String> getStepsToReproduce();

    void addStepToReproduce(List<String> steps);


    void changeBugPriority(Priority priority);
    void changeBugSeverity(Severity severity);
    void changeBugStatus(BugStatus bugStatus);


}
