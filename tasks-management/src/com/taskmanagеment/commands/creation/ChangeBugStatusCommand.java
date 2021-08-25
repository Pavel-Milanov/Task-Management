package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.enums.BugStatus;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugStatusCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeBugStatusCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        BugStatus status = ParsingHelpers.tryParseEnum(parameters.get(1),BugStatus.class );
        return changeStatus(bugId,status);
    }

    private String changeStatus(int bugId, BugStatus status) {
        Bug bug = taskManagementRepository.findElementById(taskManagementRepository.getBugs(),bugId);
        bug.changeBugStatus(status);
        return String.format(CommandConstants.STATUS_CHANGED_SUCCESSFULLY,bug.getName());
    }
    //Pavel
}
