package com.taskmanagеment.commands.creation;

import com.taskmanagеment.commands.contracts.Command;
import com.taskmanagеment.constants.CommandConstants;
import com.taskmanagеment.core.contacts.TaskManagementRepository;
import com.taskmanagеment.models.contracts.Bug;
import com.taskmanagеment.models.contracts.Member;
import com.taskmanagеment.models.enums.Priority;
import com.taskmanagеment.utils.ParsingHelpers;
import com.taskmanagеment.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugPriorityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public ChangeBugPriorityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_TASK_INDEX);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1),Priority.class );
        return changePriority(bugId,priority);
    }

    private String changePriority(int bugId, Priority priority) {
        Bug bug = taskManagementRepository.findElementById(taskManagementRepository.getBugs(),bugId);
        bug.changeBugPriority(priority);
        return String.format(CommandConstants.PRIORITY_CHANGED_SUCCESSFULLY,bug.getName());
    }
    //Pavel
}
