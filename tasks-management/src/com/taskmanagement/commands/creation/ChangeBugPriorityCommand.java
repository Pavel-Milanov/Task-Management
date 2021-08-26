package com.taskmanagement.commands.creation;

import com.taskmanagement.commands.contracts.Command;
import com.taskmanagement.constants.CommandConstants;
import com.taskmanagement.core.contacts.TaskManagementRepository;
import com.taskmanagement.models.contracts.Bug;
import com.taskmanagement.models.enums.Priority;
import com.taskmanagement.utils.ParsingHelpers;
import com.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugPriorityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeBugPriorityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
        return changePriority(bugId, priority);
    }

    private String changePriority(int bugId, Priority priority) {
        Bug bug = taskManagementRepository.findElementById(taskManagementRepository.getBugs(), bugId);
        bug.changePriority(priority);
        return String.format(CommandConstants.PRIORITY_CHANGED_SUCCESSFULLY, bug.getName());
    }
    //Pavel
}
