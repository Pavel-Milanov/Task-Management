package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.Severity;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

import static com.taskmanagement.constants.CommandConstants.INVALID_TASK_INDEX;
import static com.taskmanagement.constants.CommandConstants.LABEL_CHANGED_SUCCESSFULLY;

public class ChangeBugSeverityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ChangeBugSeverityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_INDEX);

        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(1).toUpperCase(), Severity.class);

        return changeSeverity(bugId, severity);
    }

    private String changeSeverity(int bugId, Severity severity) {

        Bug bug = helperRepository.findElementById(taskManagementRepository.getBugs(), bugId);

        bug.changeBugSeverity(severity);

        return LABEL_CHANGED_SUCCESSFULLY;
    }

    //Ralitsa
}
