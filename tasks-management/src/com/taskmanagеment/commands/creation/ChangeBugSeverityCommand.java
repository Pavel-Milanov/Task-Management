package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.enums.Severity;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagеment.constants.CommandConstants.*;

public class ChangeBugSeverityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeBugSeverityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);

        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(0).toUpperCase(),Severity.class);

        return changeSeverity(bugId,severity);
    }

    private String changeSeverity(int bugId, Severity severity) {

        Bug bug = taskManagementRepository.findElementById(taskManagementRepository.getBugs(),bugId);

        bug.changeBugSeverity(severity);

        return LABEL_CHANGED_SUCCESSFULLY;
    }

    //Ralitsa
}
