package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.TaskManagementHelperRepositoryImpl;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.BugStatus;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugStatusCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private final TaskManagementHelperRepositoryImpl helperRepository;

    public ChangeBugStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
        this.helperRepository = new TaskManagementHelperRepositoryImpl(taskManagementRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        BugStatus status = ParsingHelpers.tryParseEnum(parameters.get(1), BugStatus.class);
        return changeStatus(bugId, status);
    }

    private String changeStatus(int bugId, BugStatus status) {
        Bug bug = helperRepository.findElementById(taskManagementRepository.getBugs(), bugId);
        bug.changeBugStatus(status);
        return String.format(CommandConstants.STATUS_CHANGED_SUCCESSFULLY, bug.getName());
    }
    //Pavel
}
